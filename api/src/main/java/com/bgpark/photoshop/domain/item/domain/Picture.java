package com.bgpark.photoshop.domain.item.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("P")
public class Picture extends Item {

    private String artist;

    private String imageUrl;

    public Picture() { }

    public Picture(String artist, String imageUrl) {
        this.artist = artist;
        this.imageUrl = imageUrl;
    }

    @Builder
    public Picture(String name, int price, int stockQuantity, String artist, String imageUrl) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.imageUrl = imageUrl;
    }
}

