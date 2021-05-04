package com.bgpark.photoshop.domain.auth.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.auth.dto.AuthRequest;
import com.bgpark.photoshop.domain.user.dto.UserRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static com.bgpark.photoshop.domain.auth.step.AuthStep.*;
import static com.bgpark.photoshop.domain.user.step.UserStep.사용자_생성되어_있음;

@DisplayName("인증 관련 인수 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    AuthRequest 로그인_정보;

    @BeforeEach
    void setUp() {
        String email = "bgpar82@gmail.com";
        String password = "password";
        String name = "박병길";
        사용자_생성되어_있음(사용자_생성(name,email, password));
        로그인_정보 = 로그인_정보_생성(email, password);
    }

    @DisplayName("로그인을 한다")
    @Test
    void login() {
        // when
        ExtractableResponse<Response> response = 로그인_요청(로그인_정보);

        // then
        로그인_요청_됨(response);
    }

    public UserRequest 사용자_생성(String name, String email, String password) {
        UserRequest request = new UserRequest();
        ReflectionTestUtils.setField(request, "name", name);
        ReflectionTestUtils.setField(request, "email", email);
        ReflectionTestUtils.setField(request, "password", password);
        return request;
    }
}
