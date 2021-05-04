package com.bgpark.photoshop.domain.order.dto;

import com.bgpark.photoshop.domain.order.domain.Orders;
import com.bgpark.photoshop.domain.user.dto.UserResponse;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class OrderResponse {

    private Long id;
    private UserResponse user;
    private List<OrderItemResponse> orderItems;
    private DeliveryResponse delivery;

    public static OrderResponse create(Orders order) {
        List<OrderItemResponse> orderItems = order
                .getOrderItems().stream()
                .map(OrderItemResponse::create)
                .collect(Collectors.toList());

        UserResponse user = UserResponse.of(order.getUser());

        DeliveryResponse delivery = DeliveryResponse.create(order.getDelivery());

        OrderResponse response = new OrderResponse();
        response.id = order.getId();
        response.user = user;
        response.orderItems = orderItems;
        response.delivery = delivery;
        return response;
    }
}
