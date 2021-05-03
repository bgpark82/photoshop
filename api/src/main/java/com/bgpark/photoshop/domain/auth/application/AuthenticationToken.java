package com.bgpark.photoshop.domain.auth.application;

import lombok.Getter;

@Getter
public class AuthenticationToken {

    private String principal;
    private String credential;

    public AuthenticationToken(String username, String password) {
        this.principal = username;
        this.credential = password;
    }
}
