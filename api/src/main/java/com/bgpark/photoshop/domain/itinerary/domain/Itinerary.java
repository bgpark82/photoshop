package com.bgpark.photoshop.domain.itinerary.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Itinerary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Embedded
    private Schedules schedules = new Schedules();

    public static Itinerary create(String name) {
        Itinerary itinerary = new Itinerary();
        itinerary.name = name;
        return itinerary;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules.setSchedules(schedules);
    }

    public List<Schedule> getSchedules() {
        return schedules.getSchedules();
    }
}
