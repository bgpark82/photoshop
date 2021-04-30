package com.bgpark.photoshop.domain.itinerary.application;

import com.bgpark.photoshop.domain.itinerary.domain.Itinerary;
import com.bgpark.photoshop.domain.itinerary.domain.ItineraryRepository;
import com.bgpark.photoshop.domain.itinerary.domain.Schedule;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryRequest;
import com.bgpark.photoshop.domain.itinerary.dto.ItineraryResponse;
import com.bgpark.photoshop.domain.itinerary.dto.ScheduleResponse;
import com.bgpark.photoshop.domain.place.dto.ScheduleRequest;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.bgpark.photoshop.domain.itinerary.step.ItineraryStep.일정_저장;
import static com.bgpark.photoshop.domain.itinerary.step.ScheduleStep.스케쥴_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@DisplayName("일정 관련 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class ItineraryServiceTest {

    ItineraryService itineraryService;
    @Mock ItineraryRepository itineraryRepository;

    ScheduleRequest 남산_일정, 경복궁_일정;
    ItineraryRequest 서울여행;

    @BeforeEach
    void setUp() {
        itineraryService = new ItineraryService(itineraryRepository);
        남산_일정 = 스케쥴_생성(1L, 1);
        경복궁_일정 = 스케쥴_생성(2L, 2);
        서울여행 = 일정_저장("서울여행", 남산_일정, 경복궁_일정);
    }

    @DisplayName("일정을 저장한다")
    @Test
    void save() {
        // given
        Schedule firstSchedule = createSchedule(1L, 1);
        Schedule secondSchedule = createSchedule(2L, 2);
        Itinerary seoul = createItinerary("서울여행", firstSchedule, secondSchedule);
        when(itineraryRepository.save(any())).thenReturn(seoul);

        // when
        ItineraryResponse response = itineraryService.save(서울여행);

        // then
        assertThat(response.getName()).isEqualTo("서울여행");
        assertThat(response.getSchedules()).containsExactly(
                        ScheduleResponse.create(1L, 1),
                        ScheduleResponse.create(2L, 2));
    }

    private Itinerary createItinerary(String name, Schedule... schedules) {
        Itinerary itinerary = new Itinerary();
        setField(itinerary, "name", name);
        setField(itinerary, "schedules", Lists.newArrayList(schedules));
        return itinerary;
    }

    private Schedule createSchedule(Long placeId, int seq) {
        Schedule schedule = new Schedule();
        setField(schedule, "placeId", placeId);
        setField(schedule, "seq", seq);
        return schedule;
    }
}