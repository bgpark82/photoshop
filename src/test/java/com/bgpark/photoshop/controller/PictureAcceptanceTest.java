package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.AcceptanceTest;
import com.bgpark.photoshop.domain.item.Picture;
import com.bgpark.photoshop.dto.PictureDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사진 관련 인수테스트")
class PictureAcceptanceTest extends AcceptanceTest {

    @DisplayName("사진을 저장한다")
    @Test
    void save() {
        // when
        ExtractableResponse<Response> response = 사진_저장요청();

        // then
        사진_저장요청됨(response);
    }

    private void 사진_저장요청됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(PictureDto.Res.class).getId()).isEqualTo(1L);
    }

    private Picture createPicture() {
        return Picture.builder()
                .artist("bgpark")
                .imageUrl("www.google.com")
                .name("nigh owl")
                .price(1000)
                .build();
    }

    private ExtractableResponse<Response> 사진_저장요청() {
        return RestAssured
                .given().log().all()
                    .body(createPicture())
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/pictures")
                .then().log().all().extract();
    }
}