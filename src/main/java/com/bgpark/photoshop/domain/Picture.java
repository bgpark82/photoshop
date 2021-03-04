package com.bgpark.photoshop.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("P")
@ToString(callSuper = true)
public class Picture extends Item{

    private String artist;

    @Builder
    public Picture(String name, int price, String artist) {
        super(name, price);
        this.artist = artist;
    }
}

