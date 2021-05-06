package com.bgpark.photoshop.domain.order.dto;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderRequest {

    private List<OrderItemRequest> orderItems = new ArrayList<>();

    public static OrderRequest create(OrderItemRequest... orderItems) {
        OrderRequest request = new OrderRequest();
        request.orderItems = Lists.newArrayList(orderItems);
        return request;
    }
}
