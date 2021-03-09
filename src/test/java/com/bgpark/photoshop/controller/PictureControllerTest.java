package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.domain.item.Picture;
import com.bgpark.photoshop.dto.PictureDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사진 관련 인수테스트")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PictureControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        if(RestAssured.port == RestAssured.UNDEFINED_PORT) {
            RestAssured.port = port;
        }
    }

    @DisplayName("사진을 저장한다")
    @Test
    void save() {
        // given
        Picture picture = Picture.builder()
                .artist("bgpark")
                .imageUrl("www.google.com")
                .name("nigh owl")
                .price(1000)
                .build();

        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                    .body(picture)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/api/v1/pictures")
                .then().log().all().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(PictureDto.Res.class).getId()).isEqualTo(1L);
    }
}