package com.bgpark.photoshop.domain.user.application;

import com.bgpark.photoshop.domain.auth.application.AuthenticationConverter;
import com.bgpark.photoshop.domain.auth.application.AuthenticationToken;
import com.bgpark.photoshop.domain.auth.application.SessionAuthenticationConverter;
import com.bgpark.photoshop.domain.auth.ui.SessionAuthenticationInterceptor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@DisplayName("세션 인터셉터 관련 테스트")
public class SessionAuthenticationInterceptorTest {

    SessionAuthenticationInterceptor interceptor;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    AuthenticationConverter converter;

    @BeforeEach
    void setUp() {
        interceptor = new SessionAuthenticationInterceptor();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        converter = new SessionAuthenticationConverter();
    }

    @DisplayName("로그인 요청을 받아 session에 사용자 정보를 담고 응답한다")
    @Test
    void preHandler() throws Exception {
        // when
        interceptor.preHandle(request, response, new Object());

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK); // filter를 타지않고 요청을 보냈기 때문에 JSESSIONID는 가지지 않는다
    }

    @DisplayName("Username, Password를 받아 AuthenticationToken을 생성한다")
    @Test
    void convertAuthenticationToken() {
        // given
        String username = "bgpark82@gmail.com";
        String password = "password";
        request.setParameter("username",username);
        request.setParameter("password",password);

        // when
        AuthenticationToken token = converter.convert(request);

        // then
        assertThat(token.getPrincipal()).isEqualTo(username);
        assertThat(token.getCredential()).isEqualTo(password);
    }

    @DisplayName("AuthenticationToken으로 회원가입된 사용자인지 확인 후, Authentication을 생성한다")
    @Test
    void authenticate() {

    }

    @DisplayName("Authentication으로 SecurityContext에 만들어 Session에 담는다")
    @Test
    void setSecurityContext() {
    }
}
