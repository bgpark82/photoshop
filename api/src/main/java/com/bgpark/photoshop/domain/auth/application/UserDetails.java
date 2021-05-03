package com.bgpark.photoshop.domain.auth.application;

import lombok.Getter;

@Getter
public class UserDetails {

    private Long id;
    private String principal;
    private String credential;
}
