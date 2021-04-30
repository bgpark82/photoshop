package com.bgpark.photoshop.domain.place.step;

import com.bgpark.photoshop.domain.place.domain.Place;
import com.bgpark.photoshop.domain.place.dto.PlaceRequest;
import com.bgpark.photoshop.domain.place.dto.PlaceResponse;
import io.restassured.RestAssured;
import io.restassured.mapper.TypeRef;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class PlaceStep {

    public static ExtractableResponse<Response> 장소_저장_요청(PlaceRequest place) {
        return RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(place)
                .when().post("/api/v1/places")
                .then().log().all().extract();
    }

    public static ExtractableResponse<Response> 장소_키워드_조회_요청(String keyword) {
        return RestAssured
                .given().log().all()
                .param("keyword", keyword)
                .when().get("/api/v1/places/search")
                .then().log().all().extract();
    }

    public static void 장소_키워드_조회_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.as(new TypeRef<List<PlaceResponse>>() {})).containsExactly( // JsonPath: https://github.com/json-path/JsonPath#what-is-returned-when
                장소_응답_생성(1L, "남산", 37.5537747, 126.9722148),
                장소_응답_생성(2L, "남포동", 35.0963437,129.0287312));
    }

    public static void 장소_저장_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("location")).isEqualTo("/place/1");
    }

    public static PlaceResponse 장소_저장되어_있음(String name, double lat, double lng) {
        PlaceRequest place = 장소_생성(name, lat, lng);
        return 장소_저장_요청(place).as(PlaceResponse.class);
    }

    public static PlaceRequest 장소_생성(String name, double lat, double lng) {
        return PlaceRequest.create(name, lat, lng);
    }

    public static Place 장소_엔티티_생성(long placeId, String name, double lat, double lng) {
        Place place = new Place();
        setField(place, "id", placeId);
        setField(place, "name", name);
        setField(place, "lat", lat);
        setField(place, "lng", lng);
        return place;
    }

    public static PlaceResponse 장소_응답_생성(long placeId, String name, double lat, double lng) {
        PlaceResponse response = new PlaceResponse();
        setField(response, "id", placeId);
        setField(response, "name", name);
        setField(response, "lat", lat);
        setField(response, "lng", lng);
        return response;
    }
}
