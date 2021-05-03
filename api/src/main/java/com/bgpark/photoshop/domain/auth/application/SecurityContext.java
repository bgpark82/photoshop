package com.bgpark.photoshop.domain.auth.application;

import lombok.Getter;

@Getter
public class SecurityContext {

    private Authentication authentication;

    public SecurityContext(Authentication authentication) {
        this.authentication = authentication;
    }
}
