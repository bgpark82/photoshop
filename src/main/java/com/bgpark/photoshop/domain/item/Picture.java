package com.bgpark.photoshop.domain.item;

import com.bgpark.photoshop.domain.item.Item;
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

    @Builder
    public Picture(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}

