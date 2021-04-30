package com.bgpark.photoshop.domain.itinerary.dto;

import com.bgpark.photoshop.domain.itinerary.domain.Itinerary;
import lombok.Getter;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
public class ItineraryResponse {

    private String name;
    private List<ScheduleResponse> schedules;

    public static ItineraryResponse of(Itinerary itinerary) {
        ItineraryResponse response = new ItineraryResponse();
        response.name = itinerary.getName();
        response.schedules = itinerary.getSchedules().stream()
                .map(ScheduleResponse::of)
                .collect(toList());
        return response;
    }
}
