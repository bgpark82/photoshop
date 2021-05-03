package com.bgpark.photoshop.domain.auth.application;

import lombok.Getter;

@Getter
public class UserDetails {

    private Long id;
    private String principal;
    private String credential;

    public static UserDetails of(Long id, String email, String password) {
        UserDetails details = new UserDetails();
        details.id = id;
        details.principal = email;
        details.credential = password;
        return details;
    }
}
