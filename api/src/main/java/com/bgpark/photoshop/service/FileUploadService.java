package com.bgpark.photoshop.service;

import com.bgpark.photoshop.dto.upload.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.bgpark.photoshop.domain.place.MediaType.photo;

public class FileUploadService {

    public File create(MultipartFile multipartFile) {
        String ext = getExtension(multipartFile.getOriginalFilename());
        String fileName = String.format("%s.%s", UUID.randomUUID().toString(), ext);
        String tmpdir = System.getProperty("java.io.tmpdir");
        File storeFile = new File(tmpdir, fileName);
        try {
            multipartFile.transferTo(storeFile);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("파일이 생성되지 않았습니다");
        }
        return storeFile;
    }

    public String getExtension(String fileName) {
        int position = fileName.lastIndexOf(".");
        String ext = fileName.substring(position + 1);
        if(ext.length() < 1) ext = "jpg";
        return ext;
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
