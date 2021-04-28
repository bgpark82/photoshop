package com.bgpark.photoshop.domain.file.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.bgpark.photoshop.domain.file.step.FileStep.이미지_파일_생성;
import static com.bgpark.photoshop.domain.file.step.FileUploadStep.이미지_저장_요청;

@DisplayName("파일 업로드 관련 인수 테스트")
public class FileUploadAcceptanceTest extends AcceptanceTest {

    private MultiPartSpecification 이미지;

    // TODO: 업로드 이후 삭제
    @BeforeEach
    void setUp() {
        이미지 = new MultiPartSpecBuilder(이미지_파일_생성()).build();
    }

    @DisplayName("이미지를 업로드한다")
    @Test
    void fileUpload() {
        // when
        ExtractableResponse<Response> response = 이미지_저장_요청(이미지);

        // then
        이미지_저장_요청됨(response);
    }

    private void 이미지_저장_요청됨(ExtractableResponse<Response> response) {
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
