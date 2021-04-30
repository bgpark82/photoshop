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

    @OneToMany(cascade = CascadeType.PERSIST)
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
