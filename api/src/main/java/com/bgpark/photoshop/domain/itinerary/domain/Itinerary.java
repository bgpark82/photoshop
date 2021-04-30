package com.bgpark.photoshop.domain.itinerary.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Itinerary {

    private String name;
    private List<Schedule> schedules;

    public static Itinerary create(String name) {
        Itinerary itinerary = new Itinerary();
        itinerary.name = name;
        return itinerary;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
