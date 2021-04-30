package com.bgpark.photoshop.domain.itinerary.dto;

import lombok.Getter;

@Getter
public class ScheduleResponse {

    private Long placeId;
    private int seq;

    public static ScheduleResponse create(Long placeId, int seq) {
        ScheduleResponse response = new ScheduleResponse();
        response.placeId = placeId;
        response.seq = seq;
        return response;
    }
}
