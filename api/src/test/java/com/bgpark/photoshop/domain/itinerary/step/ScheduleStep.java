package com.bgpark.photoshop.domain.itinerary.step;

import com.bgpark.photoshop.domain.place.dto.ScheduleRequest;

public class ScheduleStep {

    public static ScheduleRequest 스케쥴_생성(Long placeId, int seq) {
        return ScheduleRequest.create(placeId, seq);
    }
}
