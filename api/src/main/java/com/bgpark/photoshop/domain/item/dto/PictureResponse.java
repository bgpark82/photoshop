package com.bgpark.photoshop.domain.item.dto;

import com.bgpark.photoshop.domain.item.domain.Picture;
import lombok.Getter;

@Getter
public class PictureResponse {

    private Long id;
    private String name;
    private String artist;
    private String imageUrl;
    private int price;

    public static PictureResponse of(Picture p) {
        PictureResponse response = new PictureResponse();
        response.id = p.getId();
        response.name = p.getName();
        response.artist = p.getArtist();
        response.imageUrl = p.getImageUrl();
        response.price = p.getPrice();
        return response;
    }
}
