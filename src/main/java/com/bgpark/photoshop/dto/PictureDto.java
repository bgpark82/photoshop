package com.bgpark.photoshop.dto;

import com.bgpark.photoshop.domain.item.Item;
import com.bgpark.photoshop.domain.item.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PictureDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Req {
        private String name;
        private String artist;
        private String imageUrl;
        private int price;

        public Item toEntity() {
            return Picture.builder()
                    .price(price)
                    .name(name)
                    .imageUrl(imageUrl)
                    .artist(artist)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {
        private Long id;
        private String name;
        private String artist;
        private String imageUrl;
        private int price;

        public static PictureDto.Res of(Picture p) {
            return new PictureDto.Res(p.getId(), p.getName(), p.getArtist(), p.getImageUrl(), p.getPrice());
        }
    }
}
