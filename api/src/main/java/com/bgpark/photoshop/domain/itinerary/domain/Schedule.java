package com.bgpark.photoshop.domain.itinerary.domain;

import com.bgpark.photoshop.domain.place.dto.ScheduleRequest;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class Schedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long placeId;
    private int seq;

    public static Schedule create(ScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.placeId = request.getPlaceId();
        schedule.seq = request.getSeq();
        return schedule;
    }
}
