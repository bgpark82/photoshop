package com.bgpark.photoshop.domain.place.step;

import com.bgpark.photoshop.domain.place.dto.PlaceRequest;
import com.bgpark.photoshop.domain.place.dto.PlaceResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class PlaceStep {

    public static ExtractableResponse<Response> 장소_저장_요청(PlaceRequest place) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(place)
                .when().post("/api/v1/places")
                .then().log().all().extract();
    }

    public static PlaceResponse 장소_저장되어_있음(String name, double lat, double lng) {
        PlaceRequest place = 장소_생성(name, lat, lng);
        return 장소_저장_요청(place).as(PlaceResponse.class);
    }

    public static PlaceRequest 장소_생성(String name, double lat, double lng) {
        return PlaceRequest.create(name, lat, lng);
    }
}
