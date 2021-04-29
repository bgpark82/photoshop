package com.bgpark.photoshop.domain.place.dto;

import com.bgpark.photoshop.domain.place.domain.Place;

public class PlaceRequest {

    private String name;
    private double lat;
    private double lng;

    public static PlaceRequest create(String name, double lat, double lng) {
        PlaceRequest request = new PlaceRequest();
        request.name = name;
        request.lat = lat;
        request.lng = lng;
        return request;
    }

    public Place of() {
        return Place.create(name, lat, lng);
    }
}
