package com.bgpark.photoshop.domain.user.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.user.dto.AddressDto;
import com.bgpark.photoshop.domain.user.dto.UserDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Set;

import static com.bgpark.photoshop.domain.user.step.UserStep.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자 관련 인수 테스트")
public class UserAcceptanceTest extends AcceptanceTest {

    AddressDto.SaveReq 사용자_집주소, 사용자_회사주소;
    Set<String> 사용자_관심분야;
    String 이름, 이메일, 비밀번호;

    @BeforeEach
    void setUp() throws Exception {
        super.beforeEach();
        이름 = "박병길";
        이메일 = "bgpark82@gmail.com";
        비밀번호 = "password";
        사용자_집주소 = 사용자_집주소("서울", "가산동", "롯데백화점", 2468);
        사용자_회사주소 = 사용자_회사주소("서울", "가산동", "롯데백화점", 2468);
        사용자_관심분야 = 사용자_관심분야("portrait", "landscape");
    }

    @DisplayName("사용자를 생성한다")
    @Test
    void create() {
        UserDto.Req request = 사용자(이름, 이메일, 비밀번호, 사용자_집주소, 사용자_회사주소, 사용자_관심분야);

        // when
        ExtractableResponse<Response> response = 사용자_생성요청(request);

        // then
        사용자_생성_됨(response);
    }

    private void 사용자_생성_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(UserDto.Res.class).getId()).isEqualTo(1L);
        assertThat(response.body().as(UserDto.Res.class).getEmail()).isEqualTo("bgpark82@gmail.com");
        assertThat(response.body().as(UserDto.Res.class).getPassword()).isEqualTo("password");

    }
}
