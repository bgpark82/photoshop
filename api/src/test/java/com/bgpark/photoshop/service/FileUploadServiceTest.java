package com.bgpark.photoshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("File Upload 서비스 테스트 관련")
public class FileUploadServiceTest {

    private FileUploadService fileUploadService;
    private MockMultipartFile 이미지;

    @BeforeEach
    void setUp() {
        fileUploadService = new FileUploadService();
        이미지 = new MockMultipartFile("file", "poi_28010313.jpg", MediaType.IMAGE_JPEG_VALUE, "poi_28010313.jpg".getBytes());
    }

    @DisplayName("파일을 생성한다")
    @Test
    void create() {
        // when
        File file = fileUploadService.create(이미지);

        // then
        assertThat(file).exists();
        assertThat(file.isFile()).isTrue();
    }

    @DisplayName("Multipart 타입을 체크한다")
    @Test
    void checkType() {
        assertThat(이미지.getContentType()).isEqualTo("image/jpeg");
        assertThat(이미지.getOriginalFilename()).isEqualTo("poi_28010313.jpg");
    }

    @DisplayName("파일의 확장자를 구한다")
    @Test
    void getFileExtension() {
        // when
        String ext = fileUploadService.getExtension(이미지.getOriginalFilename());

        // then
        assertThat(ext).isEqualTo("jpg");
    }
}

