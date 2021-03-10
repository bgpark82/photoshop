package com.bgpark.photoshop.step;

import com.bgpark.photoshop.domain.item.Picture;
import com.bgpark.photoshop.dto.PictureDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class PictureStep {

    public static Picture 사진(String artist, String imageUrl, String name, int price) {
        return Picture.builder()
                .artist(artist)
                .imageUrl(imageUrl)
                .name(name)
                .price(price)
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
                .body(사진("bgpark", "www.google.com", "nigh owl", 1000))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/pictures")
                .then().log().all().extract();
    }
}
