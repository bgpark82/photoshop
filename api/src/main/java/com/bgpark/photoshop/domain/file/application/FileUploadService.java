package com.bgpark.photoshop.domain.file.application;

import com.bgpark.photoshop.domain.common.S3Uploader;
import com.bgpark.photoshop.domain.file.dto.UploadResponse;
import com.bgpark.photoshop.exception.EmptyMultipartException;
import com.bgpark.photoshop.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FileUploadService {

    private final S3Uploader s3Uploader;

    public UploadResponse uploadS3(MultipartFile multipartFile) throws IOException, InterruptedException {
        checkMultipart(multipartFile);
        final File file = FileUtils.convert(multipartFile);
        return s3Uploader.upload(file);
    }

    private void checkMultipart(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            throw new EmptyMultipartException();
        }
    }

}
