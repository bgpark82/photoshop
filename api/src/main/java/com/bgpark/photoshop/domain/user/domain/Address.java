package com.bgpark.photoshop.domain.user.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class Address {

    private String city;
    private String street;
    private String detail;
    private int zipcode;

    protected Address() { }

    @Builder(builderMethodName = "entityBuilder")
    public Address(String city, String street, String detail, int zipcode) {
        this.city = city;
        this.street = street;
        this.detail = detail;
        this.zipcode = zipcode;
    }
}
