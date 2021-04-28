package com.bgpark.photoshop.domain.file.application;

import com.bgpark.photoshop.domain.file.dto.UploadResponse;
import com.bgpark.photoshop.exception.EmptyMultipartException;
import com.bgpark.photoshop.utils.FileUtils;
import com.bgpark.photoshop.domain.common.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.domain.place.domain.MediaType.photo;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Uploader s3Uploader;

    public UploadResponse uploadS3(MultipartFile multipartFile) throws IOException, InterruptedException {
        checkMultipart(multipartFile);
        File file = FileUtils.convert(multipartFile);
        return s3Uploader.upload(file);
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

    private void checkMultipart(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new EmptyMultipartException();
        }
    }

}
