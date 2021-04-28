package com.bgpark.photoshop.domain.file.application;

import com.bgpark.photoshop.domain.common.S3Uploader;
import com.bgpark.photoshop.domain.file.dto.UploadResponse;
import com.bgpark.photoshop.exception.EmptyMultipartException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.domain.file.step.FileStep.*;
import static com.bgpark.photoshop.domain.place.domain.MediaType.photo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("File Upload 서비스 관련 테스트")
public class FileUploadServiceTest {

    private FileUploadService fileUploadService;
    private MockMultipartFile 이미지;
    private MockMultipartFile 빈_이미지;
    private S3Uploader s3Uploader;

    @BeforeEach
    void setUp() throws IOException {
        s3Uploader = mock(S3Uploader.class);
        fileUploadService = new FileUploadService(s3Uploader);
        빈_이미지 = 빈_이미지_멀티파트_생성();
        이미지 = 이미지_멀티파트_생성();
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

    @DisplayName("파일을 S3에 업로드한다")
    @Test
    void uploadS3() throws IOException, InterruptedException {
        // given
        when(s3Uploader.upload(any())).thenReturn(new UploadResponse(MOCK_IMAGE_HEIGHT, MOCK_IMAGE_WIDTH, MOCK_IMAGE_SIZE, photo, MOCK_IMAGE_NAME));

        // when
        UploadResponse response = fileUploadService.uploadS3(이미지);

        // then
        assertThat(response.getHeight()).isEqualTo(MOCK_IMAGE_HEIGHT);
    }

    @DisplayName("빈 Multipart 파일을 업로드하면 Exception 발생한다")
    @Test
    void emptyMultipart() {
        assertThatThrownBy(() -> fileUploadService.uploadS3(빈_이미지))
                .isInstanceOf(EmptyMultipartException.class);
    }
}

