package com.bgpark.photoshop.domain.order.dto;

import com.bgpark.photoshop.domain.user.domain.Address;
import com.bgpark.photoshop.domain.order.domain.Delivery;
import com.bgpark.photoshop.domain.order.domain.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeliveryDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Res {
        private Long id;
        private Address address;
        private DeliveryStatus status;

        public static Res of(Delivery delivery) {
            return new Res(delivery.getId(), delivery.getAddress(), delivery.getStatus());
        }
    }
}
