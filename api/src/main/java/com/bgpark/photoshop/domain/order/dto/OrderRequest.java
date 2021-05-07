package com.bgpark.photoshop.domain.order.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderRequest {

    private List<OrderItemRequest> orderItems = new ArrayList<>();

}
