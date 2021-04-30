package com.bgpark.photoshop.domain.place.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@EqualsAndHashCode
public class Place {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double lat;
    private double lng;

    public static Place create(String name, double lat, double lng) {
        Place place = new Place();
        place.name = name;
        place.lat = lat;
        place.lng = lng;
        return place;
    };
}
