package com.bgpark.photoshop.domain.item.domain;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("P")
public class Picture extends Item {

    private String artist;

    private String imageUrl;

    protected Picture() { }

    @Builder(builderMethodName = "entityBuilder")
    public Picture(String name, int price, int stockQuantity, String artist, String imageUrl) {
        super(name, price, stockQuantity);

        Assert.hasText(artist, "작성자 이름이 없습니다.");
        this.artist = artist;
        this.imageUrl = imageUrl;
    }
}

