package com.bgpark.photoshop.domain.place.dto;

import com.bgpark.photoshop.domain.place.domain.Place;
import lombok.Getter;

@Getter
public class PlaceResponse {

    private Long id;
    private String name;
    private double lat;
    private double lng;

    public static PlaceResponse of(Place p) {
        PlaceResponse response = new PlaceResponse();
        response.id = p.getId();
        response.name = p.getName();
        response.lat = p.getLat();
        response.lng = p.getLng();
        return response;
    }
}
