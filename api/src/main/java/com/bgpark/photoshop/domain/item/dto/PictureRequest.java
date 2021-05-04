package com.bgpark.photoshop.domain.item.dto;

import com.bgpark.photoshop.domain.item.domain.Picture;
import lombok.Getter;

@Getter
public class PictureRequest {

    private String name;
    private int price;
    private int stockQuantity;
    private String artist;
    private String imageUrl;

    public static PictureRequest create(String name, int price, int stockQuantity, String artist, String imageUrl) {
        PictureRequest request = new PictureRequest();
        request.name = name;
        request.price = price;
        request.stockQuantity = stockQuantity;
        request.artist = artist;
        request.imageUrl = imageUrl;
        return request;
    }

    public Picture toEntity() {
        return Picture.entityBuilder()
                .price(price)
                .name(name)
                .imageUrl(imageUrl)
                .artist(artist)
                .stockQuantity(stockQuantity)
                .build();
    }
}
