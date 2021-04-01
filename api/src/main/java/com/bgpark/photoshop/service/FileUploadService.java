package com.bgpark.photoshop.service;

import com.bgpark.photoshop.dto.upload.UploadResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.domain.place.MediaType.photo;

public class FileUploadService {

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
