package com.bgpark.photoshop.domain.auth.application;

import javax.servlet.http.HttpServletRequest;

public class SessionAuthenticationConverter implements AuthenticationConverter{

    @Override
    public AuthenticationToken convert(HttpServletRequest request) {
        return null;
    }
}
