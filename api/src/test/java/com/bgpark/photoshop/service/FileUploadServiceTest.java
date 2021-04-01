package com.bgpark.photoshop.service;

import com.bgpark.photoshop.dto.upload.UploadResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.step.FileStep.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("File Upload 서비스 테스트 관련")
public class FileUploadServiceTest {

    private FileUploadService fileUploadService;
    private MockMultipartFile 이미지;

    @BeforeEach
    void setUp() throws IOException {
        fileUploadService = new FileUploadService();
        이미지 = 이미지_생성();
    }

    @DisplayName("UploadResponse를 생성한다")
    @Test
    void createUploadResponse() throws IOException {
        // given
        File file = 이미지_생성_요청(이미지);

        // when
        UploadResponse response = fileUploadService.getUploadResponse(file);

        // then
        assertThat(response.getHeight()).isEqualTo(MOCK_IMAGE_HEIGHT);
        assertThat(response.getWidth()).isEqualTo(MOCK_IMAGE_WIDTH);
        assertThat(response.getSize()).isEqualTo(MOCK_IMAGE_SIZE);
    }

    @DisplayName("Multipart 타입을 체크한다")
    @Test
    void checkType() {
        assertThat(이미지.getContentType()).isEqualTo(MediaType.IMAGE_PNG_VALUE);
        assertThat(이미지.getOriginalFilename()).isEqualTo(MOCK_IMAGE_NAME);
    }
}

