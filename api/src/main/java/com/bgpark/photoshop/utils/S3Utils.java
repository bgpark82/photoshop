package com.bgpark.photoshop.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.bgpark.photoshop.dto.upload.UploadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.domain.place.MediaType.photo;

@Component
@RequiredArgsConstructor
public class S3Utils {

    public static final String S3_BUCKET_URL = "https://s3.ap-northeast-2.amazonaws.com";
    public static final String S3_BUCKET_KEY = "media";
    public static final String S3_BUCKET_NAME = "bg-nearlog";
    private final AmazonS3 amazonS3;

    public UploadResponse upload(File file) throws InterruptedException, IOException {
        String fileName = file.getName();
        String filePath = "20210401/" + fileName;

        BufferedImage bufferedImage = ImageIO.read(file);

        TransferManager manager = TransferManagerBuilder
                .standard()
                .withS3Client(amazonS3)
                .build();

        PutObjectRequest request = new PutObjectRequest(S3_BUCKET_NAME, S3_BUCKET_KEY, file);
        Upload upload = manager.upload(request);
        upload.waitForUploadResult();

        UploadResponse response = UploadResponse.builder()
                .height(bufferedImage.getHeight())
                .width(bufferedImage.getWidth())
                .size(file.length())
                .mediaType(photo)
                .url(S3_BUCKET_URL + "/" + S3_BUCKET_NAME + "/" + filePath)
                .build();

        file.delete();
        return response;
    }
}
