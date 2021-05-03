package com.bgpark.photoshop.domain.auth.application;

import com.bgpark.photoshop.domain.auth.ui.SessionAuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class AuthConfig implements WebMvcConfigurer {

    private final AuthenticationConverter converter;
    private final UserDetailsService detailsService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionAuthenticationInterceptor(converter, detailsService)).addPathPatterns("/api/v1/login");
    }
}
