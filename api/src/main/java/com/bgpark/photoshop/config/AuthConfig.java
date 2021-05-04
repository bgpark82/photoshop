package com.bgpark.photoshop.config;

import com.bgpark.photoshop.domain.auth.application.converter.AuthenticationConverter;
import com.bgpark.photoshop.domain.auth.application.details.UserDetailsService;
import com.bgpark.photoshop.domain.auth.ui.SessionAuthenticationInterceptor;
import com.bgpark.photoshop.domain.auth.ui.SessionSecurityContextInterceptor;
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
        registry.addInterceptor(new SessionSecurityContextInterceptor());
    }
}
