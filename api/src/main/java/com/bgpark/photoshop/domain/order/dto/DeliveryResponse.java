package com.bgpark.photoshop.domain.order.dto;

import com.bgpark.photoshop.domain.order.domain.Delivery;
import com.bgpark.photoshop.domain.order.domain.DeliveryStatus;
import com.bgpark.photoshop.domain.user.domain.Address;
import lombok.Getter;

@Getter
public class DeliveryResponse {

    private Long id;
    private Address address;
    private DeliveryStatus status;

    public static DeliveryResponse create(Delivery delivery) {
        DeliveryResponse response = new DeliveryResponse();
        response.id = delivery.getId();
        response.address = delivery.getAddress();
        response.status = delivery.getStatus();
        return response;
    }
}
