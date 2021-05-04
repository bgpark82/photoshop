package com.bgpark.photoshop.domain.order.dto;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderRequest {

    private Long userId;
    private List<OrderItemRequest> orderItems = new ArrayList<>();

    public static OrderRequest create(Long userId, OrderItemRequest... orderItems) {
        OrderRequest request = new OrderRequest();
        request.userId = userId;
        request.orderItems = Lists.newArrayList(orderItems);
        return request;
    }
}
