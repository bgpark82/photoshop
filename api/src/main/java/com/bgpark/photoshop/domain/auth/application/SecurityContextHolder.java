package com.bgpark.photoshop.domain.auth.application;

import com.bgpark.photoshop.domain.auth.domain.SecurityContext;

public class SecurityContextHolder {

    public static final String SECURITY_CONTEXT_KEY = "SECURITY_CONTEXT_KEY";
    private static final ThreadLocal<SecurityContext> contextHolder;

    static {
        contextHolder = new ThreadLocal();
    }

    public static void setContext(SecurityContext context) {
        if(context != null) {
            contextHolder.set(context);
        }
    }

    public static SecurityContext getContext() {
        return contextHolder.get();
    }
}
