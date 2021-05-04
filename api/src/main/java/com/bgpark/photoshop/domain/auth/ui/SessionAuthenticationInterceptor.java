package com.bgpark.photoshop.domain.auth.ui;

import com.bgpark.photoshop.domain.auth.application.converter.AuthenticationConverter;
import com.bgpark.photoshop.domain.auth.domain.SecurityContext;
import com.bgpark.photoshop.domain.auth.domain.UserDetails;
import com.bgpark.photoshop.domain.auth.application.details.UserDetailsService;
import com.bgpark.photoshop.domain.auth.domain.Authentication;
import com.bgpark.photoshop.domain.auth.domain.AuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//https://soon-devblog.tistory.com/2
@RequiredArgsConstructor
public class SessionAuthenticationInterceptor implements HandlerInterceptor {

    private final AuthenticationConverter converter;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final AuthenticationToken authenticationToken = converter.convert(request);
        final Authentication authentication = authenticate(authenticationToken);
        store(request, authentication);
        response.setStatus(HttpServletResponse.SC_OK);
        return false;
    }

    public Authentication authenticate(AuthenticationToken token) {
        final String email = token.getPrincipal();
        final UserDetails userDetails = userDetailsService.loadByUsername(email);
        return new Authentication(userDetails);
    }

    public void store(HttpServletRequest request, Authentication authentication) {
        final HttpSession session = request.getSession();
        session.setAttribute("SECURITY_CONTEXT", new SecurityContext(authentication));
    }
}
