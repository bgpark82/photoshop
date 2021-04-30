package com.bgpark.photoshop.domain.itinerary.dto;

import com.bgpark.photoshop.domain.place.dto.ScheduleRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItineraryRequest {

    private String name;
    private List<ScheduleRequest> schedules;

    public ItineraryRequest(String name, List<ScheduleRequest> schedules) {
        this.name = name;
        this.schedules = schedules;
    }
}
