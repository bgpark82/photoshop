package com.bgpark.photoshop.domain.auth.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String email;
    private String password;

    public static AuthRequest create(String email, String password) {
        AuthRequest request = new AuthRequest();
        request.email = email;
        request.password = password;
        return request;
    }
}