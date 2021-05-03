package com.bgpark.photoshop.domain.auth.ui;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//https://soon-devblog.tistory.com/2
public class SessionAuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String email = request.getParameter("email");

        request.getSession().setAttribute("email", email);
        response.setStatus(HttpServletResponse.SC_OK);
        return false;
    }
}
