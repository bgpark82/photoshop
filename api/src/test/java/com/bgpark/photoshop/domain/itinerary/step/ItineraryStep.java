package com.bgpark.photoshop.domain.itinerary.step;

import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class ItineraryStep {

    public static ExtractableResponse<Response> 일정_저장_요청(ItineraryRequest 서울여행) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(서울여행)
                .when().post("/api/v1/itineraries")
                .then().log().all().extract();
    }

    public static ItineraryRequest 일정_저장(String name, Long placeId, int seq) {
        return new ItineraryRequest(name, placeId, seq);
    }
}
