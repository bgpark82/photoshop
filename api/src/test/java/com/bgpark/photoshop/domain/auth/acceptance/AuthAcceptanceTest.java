package com.bgpark.photoshop.domain.auth.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.auth.dto.AuthRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bgpark.photoshop.domain.auth.step.AuthStep.*;

@DisplayName("인증 관련 인수 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    AuthRequest 로그인_정보;

    @BeforeEach
    void setUp() {
        로그인_정보 = 로그인_생성("bgpar82@gmail.com", "password");
    }

    @DisplayName("로그인을 한다")
    @Test
    void login() {
        // when
        ExtractableResponse<Response> response = 로그인_요청(로그인_정보);

        // then
        로그인_요청_됨(response);
    }
}
