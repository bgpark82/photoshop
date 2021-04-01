package com.bgpark.photoshop.service;

import com.bgpark.photoshop.dto.upload.UploadResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("File Upload 서비스 테스트 관련")
public class FileUploadServiceTest {

    private static final String PATH = "src/test/resources/images";
    private static final String IMAGE_NAME = "citus.png";
    private static final int IMAGE_HEIGHT = 361;
    private static final int IMAGE_WIDTH = 544;
    private static final long IMAGE_SIZE = 19655L;

    private FileUploadService fileUploadService;
    private MockMultipartFile 이미지;

    @BeforeEach
    void setUp() throws IOException {
        fileUploadService = new FileUploadService();
        이미지 = 이미지_생성();
    }

    @DisplayName("이미지 파일을 생성한다")
    @Test
    void create() throws IOException {
        // when
        File file = 이미지_생성_요청(이미지);

        // then
        assertThat(file).exists();
        assertThat(file.isFile()).isTrue();
        assertThat(file.length()).isEqualTo(IMAGE_SIZE);
    }

    @DisplayName("UploadResponse를 생성한다")
    @Test
    void createUploadResponse() throws IOException {
        // given
        File file = 이미지_생성_요청(이미지);

        // when
        UploadResponse response = fileUploadService.getUploadResponse(file);

        // then
        assertThat(response.getHeight()).isEqualTo(IMAGE_HEIGHT);
        assertThat(response.getWidth()).isEqualTo(IMAGE_WIDTH);
        assertThat(response.getSize()).isEqualTo(IMAGE_SIZE);
    }

    @DisplayName("파일의 확장자를 구한다")
    @Test
    void getFileExtension() {
        // when
        String ext = fileUploadService.getExtension(이미지.getOriginalFilename());

        // then
        assertThat(ext).isEqualTo("png");
    }

    @DisplayName("Multipart 타입을 체크한다")
    @Test
    void checkType() {
        assertThat(이미지.getContentType()).isEqualTo(MediaType.IMAGE_PNG_VALUE);
        assertThat(이미지.getOriginalFilename()).isEqualTo(IMAGE_NAME);
    }

    private MockMultipartFile 이미지_생성() throws IOException {
        final String absolutePath = new File(String.format("%s/%s", PATH, IMAGE_NAME)).getAbsolutePath();
        return new MockMultipartFile("file", IMAGE_NAME, MediaType.IMAGE_PNG_VALUE, new FileInputStream(absolutePath));
    }

    private File 이미지_생성_요청(MockMultipartFile image) {
        return fileUploadService.create(image);
    }
}

