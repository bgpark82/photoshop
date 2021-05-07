package com.bgpark.photoshop.domain.user.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.user.dto.AddressRequest;
import com.bgpark.photoshop.domain.user.dto.UserRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.bgpark.photoshop.domain.auth.step.AuthStep.로그인_되어_있음;
import static com.bgpark.photoshop.domain.user.step.UserStep.*;

@DisplayName("사용자 관련 인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    AddressRequest 사용자_집주소, 사용자_회사주소;
    Set<String> 사용자_관심분야;
    String 이름, 이메일, 비밀번호;
    UserRequest 사용자;

    @BeforeEach
    void setUp() throws Exception {
        super.beforeEach();
        이름 = "박병길";
        이메일 = "bgpark82@gmail.com";
        비밀번호 = "password";
        사용자_집주소 = 사용자_집주소("서울", "가산동", "롯데백화점", 2468);
        사용자_회사주소 = 사용자_회사주소("서울", "가산동", "롯데백화점", 2468);
        사용자_관심분야 = 사용자_관심분야("portrait", "landscape");

        사용자 = 사용자(이름, 이메일, 비밀번호, 사용자_집주소, 사용자_회사주소, 사용자_관심분야);
    }

    @DisplayName("사용자를 생성한다")
    @Test
    void create() {
        // when
        ExtractableResponse<Response> response = 사용자_생성_요청(사용자);

        // then
        사용자_생성_됨(response);
    }

    @DisplayName("사용자를 유효하지 않은 이메일로 생성한다")
    @Test
    void createWithInvalidEmail() {
        // given
        사용자 = 사용자(이름, "email", 비밀번호, 사용자_집주소, 사용자_회사주소, 사용자_관심분야);

        // when
        ExtractableResponse<Response> response = 사용자_생성_요청(사용자);

        // then
        사용자_생성_이메일_오류발생_됨(response, "이메일 주소가 유효하지 않습니다.");
    }

    @DisplayName("사용자를 빈 이메일로 생성한다")
    @Test
    void createWithEmptyEmail() {
        // given
        사용자 = 사용자(이름, "", 비밀번호, 사용자_집주소, 사용자_회사주소, 사용자_관심분야);

        // when
        ExtractableResponse<Response> response = 사용자_생성_요청(사용자);

        // then
        사용자_생성_이메일_오류발생_됨(response, "이메일을 입력해주세요.");
    }

    @DisplayName("사용자 정보를 조회한다")
    @Test
    void findUser() {
        // given
        사용자_생성되어_있음(사용자);
        String 쿠키 = 로그인_되어_있음(이메일, 비밀번호);

        // when
        ExtractableResponse<Response> response = 사용자_조회_요청(쿠키);

        // then
        사용자_조회_됨(response);
    }
}
