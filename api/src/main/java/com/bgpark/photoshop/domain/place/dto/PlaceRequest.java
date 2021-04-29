package com.bgpark.photoshop.domain.place.dto;

import com.bgpark.photoshop.domain.place.domain.Place;

public class PlaceRequest {

    private String name;
    private double lat;
    private double lng;

    public Place of() {
        return Place.create(name, lat, lng);
    }
}
