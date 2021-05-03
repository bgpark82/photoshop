package com.bgpark.photoshop.domain.itinerary.domain;

import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Embeddable
public class Schedules {

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Schedule> schedules = new ArrayList<>();

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
