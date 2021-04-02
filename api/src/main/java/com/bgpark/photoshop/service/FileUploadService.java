package com.bgpark.photoshop.service;

import com.bgpark.photoshop.dto.upload.UploadResponse;
import com.bgpark.photoshop.utils.FileUtils;
import com.bgpark.photoshop.utils.S3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.domain.place.MediaType.photo;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Utils s3Utils;

    public UploadResponse uploadS3(MultipartFile multipartFile) throws IOException, InterruptedException {
        File file = FileUtils.convert(multipartFile);
        return s3Utils.upload(file);
    }

    public UploadResponse getUploadResponse(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);

        return UploadResponse.builder()
                .height(bufferedImage.getHeight())
                .width(bufferedImage.getWidth())
                .size(file.length())
                .mediaType(photo)
                .build();
    }
}
