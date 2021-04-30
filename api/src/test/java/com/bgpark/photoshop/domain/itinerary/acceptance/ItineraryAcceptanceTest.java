package com.bgpark.photoshop.domain.itinerary.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryResponse;
import com.bgpark.photoshop.domain.itinerary.dto.ScheduleResponse;
import com.bgpark.photoshop.domain.place.dto.PlaceResponse;
import com.bgpark.photoshop.domain.place.dto.ScheduleRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.bgpark.photoshop.domain.itinerary.step.ItineraryStep.일정_저장;
import static com.bgpark.photoshop.domain.itinerary.step.ItineraryStep.일정_저장_요청;
import static com.bgpark.photoshop.domain.itinerary.step.ScheduleStep.스케쥴_생성;
import static com.bgpark.photoshop.domain.place.step.PlaceStep.장소_저장되어_있음;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("일정 관련 인수테스트")
public class ItineraryAcceptanceTest extends AcceptanceTest {

    PlaceResponse 남산, 경복궁;
    ScheduleRequest 남산_일정, 경복궁_일정;
    ItineraryRequest 서울여행;

    @BeforeEach
    void setUp() {
        남산 = 장소_저장되어_있음("남산", 37.5537747, 126.9722148);
        경복궁 = 장소_저장되어_있음("경복궁", 37.5796212,126.974847);
        남산_일정 = 스케쥴_생성(남산.getId(), 1);
        경복궁_일정 = 스케쥴_생성(경복궁.getId(), 2);
        서울여행 = 일정_저장("서울여행", 남산_일정, 경복궁_일정);
    }

    @DisplayName("새로운 일정을 생성한다")
    @Test
    void save() {
        // when
        ExtractableResponse<Response> response = 일정_저장_요청(서울여행);

        // then
        일정_저장_됨(response);
    }

    private void 일정_저장_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()). isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("location")).isEqualTo("/itinerary/1");
        assertThat(response.as(ItineraryResponse.class).getSchedules()).containsExactly(
                ScheduleResponse.create(1L, 1),
                ScheduleResponse.create(2L, 2));
    }
}
