package com.bgpark.photoshop.domain.auth.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    private String username;
    private String password;

    public static AuthRequest create(String email, String password) {
        AuthRequest request = new AuthRequest();
        request.username = email;
        request.password = password;
        return request;
    }
}
