package com.bgpark.photoshop.domain.item.step;

import com.bgpark.photoshop.domain.item.domain.Picture;
import com.bgpark.photoshop.domain.order.dto.PictureDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class PictureStep {

    public static Picture 사진(String artist, String imageUrl, String name, int price, int stockQuantity) {
        return Picture.builder()
                .artist(artist)
                .imageUrl(imageUrl)
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

    public static Long 사진_저장되어_있음(Picture picture) {
        return RestAssured
                .given().log().all()
                .body(picture)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/pictures")
                .then().log().all().extract()
                .as(PictureDto.Res.class)
                .getId();
    }

    public static ExtractableResponse<Response> 사진_저장요청() {
        return RestAssured
                .given().log().all()
                .body(사진("bgpark", "www.google.com", "nigh owl", 1000, 100))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/pictures")
                .then().log().all().extract();
    }


    public static void 사진_저장요청됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(PictureDto.Res.class).getId()).isEqualTo(1L);
    }
}
