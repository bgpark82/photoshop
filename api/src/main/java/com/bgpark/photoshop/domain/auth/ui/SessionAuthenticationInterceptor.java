package com.bgpark.photoshop.domain.auth.ui;

import com.bgpark.photoshop.domain.auth.application.Authentication;
import com.bgpark.photoshop.domain.auth.application.AuthenticationConverter;
import com.bgpark.photoshop.domain.auth.application.AuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//https://soon-devblog.tistory.com/2
@RequiredArgsConstructor
public class SessionAuthenticationInterceptor implements HandlerInterceptor {

    private final AuthenticationConverter converter;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthenticationToken authenticationToken = converter.convert(request);
        String email = request.getParameter("email");

        request.getSession().setAttribute("email", email);
        response.setStatus(HttpServletResponse.SC_OK);
        return false;
    }

    public Authentication authenticate(AuthenticationToken token) {
        return new Authentication();
    }
}
