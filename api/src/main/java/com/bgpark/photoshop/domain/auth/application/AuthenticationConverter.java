package com.bgpark.photoshop.domain.auth.application;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationConverter {

    AuthenticationToken convert(HttpServletRequest request);
}
