package com.bgpark.photoshop.domain.common;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.bgpark.photoshop.domain.file.dto.UploadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static com.bgpark.photoshop.domain.place.domain.MediaType.photo;
import static java.time.format.DateTimeFormatter.ofPattern;

@Slf4j
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

    // ImageIO : https://docs.oracle.com/javase/tutorial/2d/images/loadimage.html
    public UploadResponse upload(File file) throws IOException {
        UploadResponse response;
        try {
            response = getUploadResponse(file, ImageIO.read(file));
            uploadToS3(file);
        } catch(IOException e) {
            throw new IOException("file upload error: " + e.getMessage());
        } finally {
            file.delete();
        }
        return response;
    }

    private UploadResponse getUploadResponse(File file, BufferedImage buffer) {
        return UploadResponse.builder()
                .height(buffer.getHeight())
                .width(buffer.getWidth())
                .size(file.length())
                .mediaType(photo)
                .url(getS3Url(file))
                .build();
    }

    // https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-s3-transfermanager.html
    private void uploadToS3(File file) {
        final String filePath = getFileNameWithPath(file);
        try {
            uploadToS3(file, filePath);
            amazonS3.setObjectAcl(S3_BUCKET_NAME, filePath, CannedAccessControlList.PublicRead);
        } catch (AmazonServiceException e) {
            log.error("Amazon service error: " + e.getMessage());
        } catch (AmazonClientException e) {
            log.error("Amazon client error: " + e.getMessage());
        } catch (InterruptedException e) {
            log.error("Transfer interrupted: " + e.getMessage());
        }
    }

    private void uploadToS3(File file, String filePath) throws InterruptedException {
        final TransferManager transferManager = getTransferManager();
        final PutObjectRequest request = new PutObjectRequest(S3_BUCKET_NAME, filePath, file);
        final Upload upload = transferManager.upload(request);
        upload.waitForCompletion();
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
