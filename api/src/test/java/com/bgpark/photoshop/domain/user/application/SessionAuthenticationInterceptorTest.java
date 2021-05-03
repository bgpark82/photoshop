package com.bgpark.photoshop.domain.user.application;

import com.bgpark.photoshop.domain.auth.ui.SessionAuthenticationInterceptor;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("세션 인터셉터 관련 테스트")
public class SessionAuthenticationInterceptorTest {

    @DisplayName("로그인 요청을 받아 Session에 사용자 정보를 담는다")
    @Test
    void preHandler() throws Exception {
        // given
        SessionAuthenticationInterceptor interceptor = new SessionAuthenticationInterceptor();
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // when
        interceptor.preHandle(request, response, new Object());

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
    }
}
