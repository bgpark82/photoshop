package com.bgpark.photoshop.domain.auth.ui;

import com.bgpark.photoshop.domain.auth.application.*;
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
        AuthenticationToken authenticationToken = converter.convert(request);
        Authentication authenticate = authenticate(authenticationToken);
        UserDetails principal = (UserDetails) authenticate.getPrincipal();

        request.getSession().setAttribute("email", principal.getPrincipal());
        response.setStatus(HttpServletResponse.SC_OK);
        return false;
    }

    public Authentication authenticate(AuthenticationToken token) {
        String email = token.getPrincipal();
        UserDetails userDetails = userDetailsService.loadByUsername(email);
        return new Authentication(userDetails);
    }

    public void store(HttpServletRequest request, Authentication authentication) {
        HttpSession session = request.getSession();
        session.setAttribute("SECURITY_CONTEXT", new SecurityContext(authentication));
    }
}
