package com.bgpark.photoshop.domain.user.dto;

import com.bgpark.photoshop.domain.user.domain.Address;
import lombok.Getter;

@Getter
public class AddressRequest {

    private String city;
    private String street;
    private String detail;
    private int zipcode;

    public static AddressRequest create(String city, String street, String detail, int zipcode) {
        AddressRequest request = new AddressRequest();
        request.city = city;
        request.street = street;
        request.detail = detail;
        request.zipcode = zipcode;
        return request;
    }

    public Address toEntity() {
        return Address.entityBuilder()
                .city(city)
                .street(street)
                .detail(detail)
                .zipcode(zipcode)
                .build();
    }
}
