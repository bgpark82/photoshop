package com.bgpark.photoshop.domain.itinerary.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ItineraryResponse {

    private String name;
    private List<ScheduleResponse> schedules;
}
