package com.bgpark.photoshop.domain.auth.ui;

import com.bgpark.photoshop.domain.auth.application.SecurityContextHolder;
import com.bgpark.photoshop.domain.auth.domain.SecurityContext;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.bgpark.photoshop.domain.auth.application.SecurityContextHolder.SECURITY_CONTEXT_KEY;

public class SessionSecurityContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (hasAuthentication()) {
            return true;
        }
        setSecurityContext(request);
        return true;
    }

    private void setSecurityContext(HttpServletRequest request) {
        SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute(SECURITY_CONTEXT_KEY);
        if (securityContext != null) {
            SecurityContextHolder.setContext(securityContext);
        }
    }

    private boolean hasAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
}
