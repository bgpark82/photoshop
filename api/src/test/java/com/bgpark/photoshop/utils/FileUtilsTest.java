package com.bgpark.photoshop.utils;

import com.bgpark.photoshop.domain.file.dto.FileType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.domain.file.step.FileStep.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FileUtils 관련 테스트")
class FileUtilsTest {

    private MockMultipartFile 이미지;

    @BeforeEach
    void setUp() throws IOException {
        이미지 = 이미지_멀티파트_생성();
    }

    @DisplayName("이미지 파일을 생성한다")
    @Test
    void create()  {
        // when
        File file = 이미지_생성_요청(이미지);

        // then
        이미지_생성됨(file);
    }


    @DisplayName("확장자가 png인 경우 photo 타입을 반환한다")
    @Test
    void getFileExtension() {
        // when
        String ext = FileUtils.getExtension(이미지.getOriginalFilename());

        // then
        assertThat(ext).isEqualTo("png");
    }

    @DisplayName("파일 타입을 구한다")
    @Test
    void getFileType() {
        // when
        FileType type = FileUtils.getFileType(이미지.getOriginalFilename());

        // then
        assertThat(type).isEqualTo(FileType.PHOTO);
    }

    private void 이미지_생성됨(File file) {
        assertThat(file).exists();
        assertThat(file.isFile()).isTrue();
        assertThat(file.length()).isEqualTo(MOCK_IMAGE_SIZE);
    }
}