package com.bgpark.photoshop.domain.place.dto;

import lombok.Getter;

@Getter
public class ScheduleRequest {

    private Long placeId;
    private int seq;

    public static ScheduleRequest create(Long placeId, int seq) {
        ScheduleRequest request = new ScheduleRequest();
        request.placeId = placeId;
        request.seq = seq;
        return request;
    }
}
