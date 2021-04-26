package com.bgpark.photoshop.domain.item.domain;

import com.bgpark.photoshop.domain.item.domain.Item;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("P")
@ToString(callSuper = true)
public class Picture extends Item {

    private String artist;

    private String imageUrl;

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

