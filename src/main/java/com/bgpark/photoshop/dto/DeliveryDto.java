package com.bgpark.photoshop.dto;

import com.bgpark.photoshop.domain.Address;
import com.bgpark.photoshop.domain.Delivery;
import com.bgpark.photoshop.domain.DeliveryStatus;
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
