package com.bgpark.photoshop.domain.itinerary.application;

import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryResponse;
import com.bgpark.photoshop.domain.itinerary.dto.ScheduleResponse;
import com.bgpark.photoshop.domain.place.dto.ScheduleRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.bgpark.photoshop.domain.itinerary.step.ItineraryStep.일정_저장;
import static com.bgpark.photoshop.domain.itinerary.step.ScheduleStep.스케쥴_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("일정 관련 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ItineraryServiceTest {

    @Mock
    ItineraryService itineraryService;

    @Test
    void save() {
        // given
        ScheduleRequest 남산_일정 = 스케쥴_생성(1L, 1);
        ScheduleRequest 경복궁_일정 = 스케쥴_생성(2L, 2);
        ItineraryRequest 서울여행 = 일정_저장("서울여행", 남산_일정, 경복궁_일정);

        // when
        ItineraryResponse response = itineraryService.save(서울여행);

        // then
        assertThat(response.getName()).isEqualTo("서울여행");
        assertThat(response.getSchedules()).containsExactly(
                        ScheduleResponse.create(1L, 1),
                        ScheduleResponse.create(2L, 2));
    }
}