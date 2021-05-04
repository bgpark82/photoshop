package com.bgpark.photoshop.domain.auth.application.converter;

import com.bgpark.photoshop.domain.auth.domain.AuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface AuthenticationConverter {

    AuthenticationToken convert(HttpServletRequest request) throws IOException;
}
