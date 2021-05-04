package com.bgpark.photoshop.domain.item.step;

import com.bgpark.photoshop.domain.item.dto.PictureRequest;
import com.bgpark.photoshop.domain.item.dto.PictureResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class PictureStep {

    public static PictureRequest 사진_생성(String artist, String imageUrl, String name, int price, int stockQuantity) {
        return PictureRequest.create(name, price, stockQuantity, artist, imageUrl);
    }

    public static PictureResponse 사진_저장되어_있음(String artist, String imageUrl, String name, int price, int stockQuantity) {
        PictureRequest request = 사진_생성(artist, imageUrl, name, price, stockQuantity);
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/pictures")
                .then().log().all().extract()
                .as(PictureResponse.class);
    }

    public static ExtractableResponse<Response> 사진_저장_요청(String artist, String imageUrl, String name, int price, int stockQuantity) {
        return RestAssured
                .given().log().all()
                .body(사진_생성(artist, imageUrl, name, price, stockQuantity))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/pictures")
                .then().log().all().extract();
    }


    public static void 사진_저장요청_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(PictureResponse.class).getId()).isEqualTo(1L);
        assertThat(response.body().as(PictureResponse.class).getArtist()).isEqualTo("bgpark");
        assertThat(response.body().as(PictureResponse.class).getImageUrl()).isEqualTo("www.google.com");
        assertThat(response.body().as(PictureResponse.class).getName()).isEqualTo("night owl");
        assertThat(response.body().as(PictureResponse.class).getPrice()).isEqualTo(1000);
    }
}
