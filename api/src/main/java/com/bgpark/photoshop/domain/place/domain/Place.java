package com.bgpark.photoshop.domain.place.domain;

public class Place {

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
