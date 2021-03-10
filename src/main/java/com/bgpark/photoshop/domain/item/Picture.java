package com.bgpark.photoshop.domain.item;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("P")
@ToString(callSuper = true)
@Table(name = "PICTURE")
public class Picture extends Item {

    private String artist;

    private String imageUrl;

    public Picture(String artist, String imageUrl) {
        this.artist = artist;
        this.imageUrl = imageUrl;
    }

    @Builder
    public Picture(String name, int price, String artist, String imageUrl) {
        super(name, price);
        this.artist = artist;
        this.imageUrl = imageUrl;
    }
}

