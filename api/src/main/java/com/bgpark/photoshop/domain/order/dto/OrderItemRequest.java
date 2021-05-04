package com.bgpark.photoshop.domain.order.dto;

import lombok.Getter;

@Getter
public class OrderItemRequest {

    private Long itemId;
    private int count;

    public static OrderItemRequest create(Long itemId, int count) {
        OrderItemRequest request = new OrderItemRequest();
        request.itemId = itemId;
        request.count = count;
        return request;
    }
}
