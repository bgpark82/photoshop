package com.bgpark.photoshop.domain.auth.ui;

import com.bgpark.photoshop.domain.auth.application.converter.AuthenticationConverter;
import com.bgpark.photoshop.domain.auth.application.converter.SessionAuthenticationConverter;
import com.bgpark.photoshop.domain.auth.domain.SecurityContext;
import com.bgpark.photoshop.domain.auth.domain.UserDetails;
import com.bgpark.photoshop.domain.auth.application.details.UserDetailsService;
import com.bgpark.photoshop.domain.auth.domain.Authentication;
import com.bgpark.photoshop.domain.auth.domain.AuthenticationToken;
import com.bgpark.photoshop.domain.auth.dto.AuthRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("세션 인터셉터 관련 테스트")
public class SessionAuthenticationInterceptorTest {

    private static final String EMAIL = "bgpark82@gmail.com";
    private static final String PASSWORD = "password";

    SessionAuthenticationInterceptor interceptor;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    AuthenticationConverter converter;
    UserDetailsService detailsService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        detailsService = mock(UserDetailsService.class);
        converter = new SessionAuthenticationConverter();
        interceptor = new SessionAuthenticationInterceptor(converter, detailsService);
        request = createMockRequest();
        response = createMockResponse();
    }

    @DisplayName("로그인 요청을 받아 session에 사용자 정보를 담고 응답한다")
    @Test
    void preHandler() throws Exception {
        // given
        when(detailsService.loadByUsername(any())).thenReturn(createUserDetails());

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
    void authenticate() throws IOException {
        // given
        AuthenticationToken token = converter.convert(request);
        when(detailsService.loadByUsername(any())).thenReturn(createUserDetails());

        // when
        Authentication authentication = interceptor.authenticate(token);

        // then
        assertThat(((UserDetails)authentication.getPrincipal()).getPrincipal()).isEqualTo(EMAIL);
    }

    @DisplayName("Authentication으로 SecurityContext를 만들어 Session에 담는다")
    @Test
    void setSecurityContext() throws IOException {
        // given
        AuthenticationToken token = converter.convert(request);
        Authentication authentication = interceptor.authenticate(token);

        // when
        interceptor.store(request, authentication);

        // then
        assertThat(((SecurityContext)request.getSession().getAttribute("SECURITY_CONTEXT")).getAuthentication())
                .isEqualTo(authentication);
    }

    private UserDetails createUserDetails() {
        UserDetails userDetails = new UserDetails();
        ReflectionTestUtils.setField(userDetails, "id", 1L);
        ReflectionTestUtils.setField(userDetails, "principal", EMAIL);
        ReflectionTestUtils.setField(userDetails, "credential", PASSWORD);
        return userDetails;
    }

    private MockHttpServletRequest createMockRequest() throws JsonProcessingException {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        AuthRequest request = AuthRequest.create(EMAIL, PASSWORD);
        servletRequest.setContent(new ObjectMapper().writeValueAsBytes(request));
        return servletRequest;
    }

    private MockHttpServletResponse createMockResponse() {
        return new MockHttpServletResponse();
    }
}
