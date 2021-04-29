package com.bgpark.photoshop.domain.itinerary.dto;

public class ItineraryRequest {

    private String name;
    private Long placeId;
    private int seq;

    public ItineraryRequest(String name, Long placeId, int seq) {
        this.name = name;
        this.placeId = placeId;
        this.seq = seq;
    }
}
