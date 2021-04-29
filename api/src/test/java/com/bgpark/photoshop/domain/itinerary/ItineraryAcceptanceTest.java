package com.bgpark.photoshop.domain.itinerary;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import com.bgpark.photoshop.domain.place.dto.PlaceRequest;
import com.bgpark.photoshop.domain.place.dto.PlaceResponse;
import com.bgpark.photoshop.domain.place.step.PlaceStep;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.bgpark.photoshop.domain.place.step.PlaceStep.장소_생성;
import static com.bgpark.photoshop.domain.place.step.PlaceStep.장소_저장되어_있음;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("일정 관련 인수테스트")
public class ItineraryAcceptanceTest extends AcceptanceTest {

    PlaceResponse 남산;

    @DisplayName("새로운 일정을 생선한다")
    @Test
    void save() {
        // given
        남산 = 장소_저장되어_있음(장소_생성("남산", 37.5537747, 126.9722148));
        ItineraryRequest 서울여행 = new ItineraryRequest("서울여행", 남산.getId(), 1);

        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(서울여행)
                .when().post("/api/v1/itineraries")
                .then().log().all().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("location")).isEqualTo("/itinerary/1");
    }
}
