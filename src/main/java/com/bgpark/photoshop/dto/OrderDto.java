package com.bgpark.photoshop.dto;

import com.bgpark.photoshop.domain.Orders;
import com.bgpark.photoshop.domain.User;
import com.bgpark.photoshop.domain.item.Picture;
import lombok.*;

import javax.persistence.criteria.Order;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    @Data
    @NoArgsConstructor
    public static class Req {
        private Long userId;
        private Long itemId;
        private int count;

        public Req(Long userId, Long itemId, int count) {
            this.userId = userId;
            this.itemId = itemId;
            this.count = count;
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {
        private Long id;
        private UserDto.Res user;
        private List<PictureDto.Res> pictures;

        public static Res of(Orders order){
            List<PictureDto.Res> pictures = order.getItems().stream()
                    .map(item -> PictureDto.Res.of((Picture) item))
                    .collect(Collectors.toList());

            UserDto.Res user = UserDto.Res.of(order.getUser());

            return Res.builder()
                    .id(order.getId())
                    .pictures(pictures)
                    .user(user)
                    .build();
        }
    }
}
