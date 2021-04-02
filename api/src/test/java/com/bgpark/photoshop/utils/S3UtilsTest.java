package com.bgpark.photoshop.utils;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.bgpark.photoshop.config.S3MockConfig;
import com.bgpark.photoshop.dto.upload.UploadResponse;
import io.findify.s3mock.S3Mock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.io.IOException;

import static com.bgpark.photoshop.step.FileStep.이미지_생성_되어있음;
import static com.bgpark.photoshop.step.S3Step.MOCK_S3_BUCKET_NAME;
import static org.assertj.core.api.Assertions.assertThat;

/** S3Utils 빈만 가져오도록 한다 (S3MockConfig와 AppConfig가 겹침) */
@SpringBootTest(classes = {S3Utils.class})
@ActiveProfiles("test")
@DisplayName("S3 관련 테스트")
@Import(S3MockConfig.class)
class S3UtilsTest {

    @Autowired private S3Mock s3;
    @Autowired private AmazonS3 client;
    private File 이미지;
    private S3Utils s3Utils;

    @BeforeEach
    void setUp() throws IOException {
        버킷_생성_되어있음(client);
        이미지 = 이미지_생성_되어있음();
        s3Utils = new S3Utils(client);
    }

    @AfterEach
    void afterAll() {
        s3.stop();
    }

    @DisplayName("S3에 이미지를 업로드한다")
    @Test
    void upload() throws InterruptedException, IOException {
        // when
        UploadResponse response = 이미지_업로드_요청();

        // then
        이미지_업로드됨(response);
    }

    private void 이미지_업로드됨(UploadResponse response) {
        assertThat(client.getObject(MOCK_S3_BUCKET_NAME, response.getUrl().substring(51)).getObjectContent()).isNotNull();
        assertThat(response.getUrl()).endsWith(이미지.getName());
    }

    private UploadResponse 이미지_업로드_요청() throws InterruptedException, IOException {
        return s3Utils.upload(이미지);
    }

    private Bucket 버킷_생성_되어있음(AmazonS3 client) {
        return client.createBucket(MOCK_S3_BUCKET_NAME);
    }
}