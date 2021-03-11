package com.bgpark.photoshop.dto;

import com.bgpark.photoshop.domain.Orders;
import com.bgpark.photoshop.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        private List<OrderItemDto.Res> orderItems;
        private DeliveryDto.Res delivery;

        public static Res of(Orders order) {
            List<OrderItemDto.Res> orderItems = order
                    .getOrderItems().stream()
                    .map(OrderItemDto.Res::of)
                    .collect(Collectors.toList());

            UserDto.Res user = UserDto.Res.of(order.getUser());

            DeliveryDto.Res delivery = DeliveryDto.Res.of(order.getDelivery());

            return Res.builder()
                    .id(order.getId())
                    .orderItems(orderItems)
                    .user(user)
                    .delivery(delivery)
                    .build();
        }
    }
}
