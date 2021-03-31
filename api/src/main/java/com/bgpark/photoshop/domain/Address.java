package com.bgpark.photoshop.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Address {

    private String city;
    private String street;
    private String detail;
    private int zipcode;

}
