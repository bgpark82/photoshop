package com.bgpark.photoshop.step;

import com.bgpark.photoshop.utils.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileStep {

    public static final String MOCK_IMAGE_PATH = "src/test/resources/images";
    public static final String MOCK_IMAGE_NAME = "citus.png";
    public static final int MOCK_IMAGE_HEIGHT = 361;
    public static final int MOCK_IMAGE_WIDTH = 544;
    public static final long MOCK_IMAGE_SIZE = 19655L;

    public static MockMultipartFile 이미지_생성() throws IOException {
        final String absolutePath = new File(String.format("%s/%s", MOCK_IMAGE_PATH, MOCK_IMAGE_NAME)).getAbsolutePath();
        return new MockMultipartFile("file", MOCK_IMAGE_NAME, MediaType.IMAGE_PNG_VALUE, new FileInputStream(absolutePath));
    }

    public static File 이미지_생성_요청(MockMultipartFile image) {
        return FileUtils.create(image);
    }
}
