package com.bgpark.photoshop.domain.itinerary.dto;

import com.bgpark.photoshop.domain.itinerary.domain.Schedule;
import lombok.EqualsAndHashCode;
import lombok.Getter;

// TODO: EqualsAndHashCode 더 알아보기
@Getter
@EqualsAndHashCode
public class ScheduleResponse {

    private Long placeId;
    private int seq;

    public static ScheduleResponse create(Long placeId, int seq) {
        ScheduleResponse response = new ScheduleResponse();
        response.placeId = placeId;
        response.seq = seq;
        return response;
    }

    public static ScheduleResponse of(Schedule schedule) {
        ScheduleResponse response = new ScheduleResponse();
        response.placeId = schedule.getPlaceId();
        response.seq = schedule.getSeq();
        return response;
    }
}
