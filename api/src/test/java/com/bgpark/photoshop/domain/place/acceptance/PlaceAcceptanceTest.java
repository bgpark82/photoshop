package com.bgpark.photoshop.domain.place.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.place.domain.Place;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 관련 인수테스트")
public class PlaceAcceptanceTest extends AcceptanceTest {

    @Test
    void save() {
        // given
        Place 남산 = Place.create("남산",37.5537747,126.9722148);

        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(남산)
                .when().post("/api/v1/places")
                .then().log().all().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("location")).isEqualTo("/place/1");
    }
}
