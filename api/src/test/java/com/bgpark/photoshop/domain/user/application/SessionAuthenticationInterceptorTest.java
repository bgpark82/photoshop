package com.bgpark.photoshop.domain.user.application;

import com.bgpark.photoshop.domain.auth.application.AuthenticationConverter;
import com.bgpark.photoshop.domain.auth.application.AuthenticationToken;
import com.bgpark.photoshop.domain.auth.application.SessionAuthenticationConverter;
import com.bgpark.photoshop.domain.auth.dto.AuthRequest;
import com.bgpark.photoshop.domain.auth.ui.SessionAuthenticationInterceptor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("세션 인터셉터 관련 테스트")
public class SessionAuthenticationInterceptorTest {

    private static final String EMAIL = "bgpark82@gmail.com";
    private static final String PASSWORD = "password";

    SessionAuthenticationInterceptor interceptor;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    AuthenticationConverter converter;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        converter = new SessionAuthenticationConverter();
        interceptor = new SessionAuthenticationInterceptor(converter);
        request = createMockRequest();
        response = new MockHttpServletResponse();
    }

    @DisplayName("로그인 요청을 받아 session에 사용자 정보를 담고 응답한다")
    @Test
    void preHandler() throws Exception {
        // when
        interceptor.preHandle(request, response, new Object());

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK); // filter를 타지않고 요청을 보냈기 때문에 JSESSIONID는 가지지 않는다
    }

    @DisplayName("Username, Password을 받아 AuthenticationToken을 생성한다")
    @Test
    void convertAuthenticationToken() throws IOException {
        // when
        AuthenticationToken token = converter.convert(request);

        // then
        assertThat(token.getPrincipal()).isEqualTo(EMAIL);
        assertThat(token.getCredential()).isEqualTo(PASSWORD);
    }

    @DisplayName("AuthenticationToken으로 회원가입된 사용자인지 확인 후, Authentication을 생성한다")
    @Test
    void authenticate() {

    }

    @DisplayName("Authentication으로 SecurityContext에 만들어 Session에 담는다")
    @Test
    void setSecurityContext() {
    }

    private MockHttpServletRequest createMockRequest() throws JsonProcessingException {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        AuthRequest request = AuthRequest.create(EMAIL, PASSWORD);
        servletRequest.setContent(new ObjectMapper().writeValueAsBytes(request));
        return servletRequest;
    }
}
