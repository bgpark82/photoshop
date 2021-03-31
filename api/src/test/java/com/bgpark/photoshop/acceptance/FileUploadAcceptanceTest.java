package com.bgpark.photoshop.acceptance;

import com.bgpark.photoshop.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("파일 업로드 관련 인수 테스트")
public class FileUploadAcceptanceTest extends AcceptanceTest {

    private MultiPartSpecification 이미지;

    @BeforeEach
    void setUp() {
        이미지= new MultiPartSpecBuilder("file.jpg".getBytes()).build();
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

    private ExtractableResponse<Response> 이미지_저장_요청(MultiPartSpecification 이미지) {
        return RestAssured
                .given().log().all()
                .multiPart(이미지)
                .when().post("/api/v1/upload")
                .then().log().all().extract();
    }
}
