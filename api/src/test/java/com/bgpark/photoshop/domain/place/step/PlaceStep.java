package com.bgpark.photoshop.domain.place.step;

import com.bgpark.photoshop.domain.place.domain.Place;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class PlaceStep {

    public static ExtractableResponse<Response> 장소_저장_요청(Place place) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(place)
                .when().post("/api/v1/places")
                .then().log().all().extract();
    }


    public static Place 장소_저장되어_있음() {
        return Place.create("남산", 37.5537747, 126.9722148);
    }
}
