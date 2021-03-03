package com.bgpark.photoshop.dto;

import com.bgpark.photoshop.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AddressDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveReq {
        private String city;
        private String street;
        private String detail;
        private int zipcode;

        public Address toEntity() {
            return Address.builder()
                    .city(city)
                    .street(street)
                    .detail(detail)
                    .zipcode(zipcode)
                    .build();
        }
    }
}
