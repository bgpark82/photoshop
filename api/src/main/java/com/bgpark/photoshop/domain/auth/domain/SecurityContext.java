package com.bgpark.photoshop.domain.auth.domain;

import com.bgpark.photoshop.domain.auth.domain.Authentication;
import lombok.Getter;

@Getter
public class SecurityContext {

    private Authentication authentication;

    public SecurityContext(Authentication authentication) {
        this.authentication = authentication;
    }

    public SecurityContext() {
    }
}
