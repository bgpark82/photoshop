package com.bgpark.photoshop.domain.order.dto;

import com.bgpark.photoshop.domain.order.domain.Orders;
import com.bgpark.photoshop.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    @Data
    @NoArgsConstructor
    public static class Req {
        private Long userId;
        private List<OrderItemDto.Req> orderItems = new ArrayList<>();

        private Req(Long userId, OrderItemDto.Req... orderItems) {
            this.userId = userId;
            this.orderItems = Arrays.asList(orderItems);
        }

        public static Req of(Long userId, OrderItemDto.Req... orderItems) {
            return new Req(userId, orderItems);
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
