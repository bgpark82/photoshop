package com.bgpark.photoshop.domain.itinerary.domain;

import com.bgpark.photoshop.domain.place.dto.ScheduleRequest;
import lombok.Getter;

@Getter
public class Schedule {

    private Long placeId;
    private int seq;

    public static Schedule create(ScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.placeId = request.getPlaceId();
        schedule.seq = request.getSeq();
        return schedule;
    }
}
