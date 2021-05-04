package com.bgpark.photoshop.domain.auth.application.converter;

import com.bgpark.photoshop.domain.auth.domain.AuthenticationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Component
public class SessionAuthenticationConverter implements AuthenticationConverter {

    private static final String EMAIL = "username";
    private static final String PASSWORD = "password";

    @Override
    public AuthenticationToken convert(HttpServletRequest request) throws IOException {
        String body = CharStreams.toString(request.getReader());
        Map<String, String> map = new ObjectMapper().readValue(body, Map.class);

        String username = map.get(EMAIL);
        String password = map.get(PASSWORD);
        return new AuthenticationToken(username, password);
    }
}
