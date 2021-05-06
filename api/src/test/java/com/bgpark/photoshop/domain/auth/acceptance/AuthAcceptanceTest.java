package com.bgpark.photoshop.domain.auth.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bgpark.photoshop.domain.auth.step.AuthStep.로그인_요청;
import static com.bgpark.photoshop.domain.auth.step.AuthStep.로그인_요청_됨;
import static com.bgpark.photoshop.domain.user.step.UserStep.사용자_생성;
import static com.bgpark.photoshop.domain.user.step.UserStep.사용자_생성되어_있음;

@DisplayName("인증 관련 인수 테스트")
public class AuthAcceptanceTest extends AcceptanceTest {

    String 이메일, 비밀번호, 이름;

    @BeforeEach
    void setUp() {
        이메일 = "bgpar82@gmail.com";
        비밀번호 = "password";
        이름 = "박병길";
        사용자_생성되어_있음(사용자_생성(이름, 이메일, 비밀번호));
    }

    @DisplayName("로그인을 한다")
    @Test
    void login() {
        // when
        ExtractableResponse<Response> response = 로그인_요청(이메일, 비밀번호);

        // then
        로그인_요청_됨(response);
    }
}
