package com.bgpark.photoshop.domain.common;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.bgpark.photoshop.domain.file.dto.UploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static com.bgpark.photoshop.domain.place.domain.MediaType.photo;
import static java.time.format.DateTimeFormatter.ofPattern;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    private static final String S3_BUCKET_URL = "https://s3.ap-northeast-2.amazonaws.com";
    private static final String S3_BUCKET_KEY = "media";
    private static final String S3_BUCKET_NAME = "bg-nearlog";
    private static final String S3_PATH_FORMATTER = "%s/%s/%s";
    private static final String FILE_PATH_FORMATTER = "%s/%s/%s";
    private static final String FILE_DATE_PATTERN = "yyyy/MM/dd";

    private final AmazonS3 amazonS3;

    public UploadResponse upload(File file) throws InterruptedException, IOException {
        final BufferedImage buffer = ImageIO.read(file);
        uploadToS3(file);

        final UploadResponse response = UploadResponse.builder()
                .height(buffer.getHeight())
                .width(buffer.getWidth())
                .size(file.length())
                .mediaType(photo)
                .url(getS3Url(file))
                .build();

        file.delete();
        return response;
    }

    private void uploadToS3(File file) throws InterruptedException {
        final Upload upload = getTransferManager()
                .upload(new PutObjectRequest(S3_BUCKET_NAME, getFileNameWithPath(file), file));

        upload.waitForUploadResult();
    }

    private String getFileNameWithPath(File file) {
        return String.format(FILE_PATH_FORMATTER, S3_BUCKET_KEY, LocalDate.now().format(ofPattern(FILE_DATE_PATTERN)), file.getName());
    }

    private String getS3Url(File file) {
        return String.format(S3_PATH_FORMATTER, S3_BUCKET_URL, S3_BUCKET_NAME, getFileNameWithPath(file));
    }

    private TransferManager getTransferManager() {
        return TransferManagerBuilder
                .standard()
                .withS3Client(amazonS3)
                .build();
    }
}
