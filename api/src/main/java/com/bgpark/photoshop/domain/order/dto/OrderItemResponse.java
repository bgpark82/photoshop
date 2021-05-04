package com.bgpark.photoshop.domain.order.dto;

import com.bgpark.photoshop.domain.item.dto.ItemResponse;
import com.bgpark.photoshop.domain.order.domain.OrderItem;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class OrderItemResponse {

    private Long id;
    private ItemResponse item;
    private LocalDateTime orderedDate;
    private int orderPrice;
    private int count;

    public static OrderItemResponse create(OrderItem orderItem) {
        OrderItemResponse response = new OrderItemResponse();
        response.id = orderItem.getId();
        response.item = ItemResponse.create(orderItem.getItem());
        response.orderedDate = orderItem.getOrderedDate();
        response.orderPrice = orderItem.getOrderPrice();
        response.count = orderItem.getCount();
        return response;
    }
}
