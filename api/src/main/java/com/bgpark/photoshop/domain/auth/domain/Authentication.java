package com.bgpark.photoshop.domain.auth.domain;

import lombok.Getter;

@Getter
public class Authentication {

    private Object principal;


    public Authentication(Object principal) {
        this.principal = principal;
    }
}
