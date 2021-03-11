package com.bgpark.photoshop.dto;

import com.bgpark.photoshop.domain.OrderItem;
import com.bgpark.photoshop.domain.Orders;
import com.bgpark.photoshop.domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class OrderItemDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {
        private Long id;
        private ItemDto item;
        private LocalDateTime orderedDate;
        private int orderPrice;
        private int count;

        public static Res of(OrderItem oi) {
            return new Res(
                    oi.getId(),
                    ItemDto.of(oi.getItem()),
                    oi.getOrderedDate(),
                    oi.getOrderPrice(),
                    oi.getCount());
        }
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ItemDto {
        // TODO: 만약 picture를 사용하고 싶다면??
        private Long id;
        private String name;
        private int price;

        public static ItemDto of(Item item) {
            return new ItemDto(item.getId(), item.getName(), item.getPrice());
        }
    }
}
