package com.bgpark.photoshop.domain.order.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.item.dto.PictureResponse;
import com.bgpark.photoshop.domain.order.dto.OrderItemRequest;
import com.bgpark.photoshop.domain.user.dto.AddressRequest;
import com.bgpark.photoshop.domain.user.dto.UserResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.bgpark.photoshop.domain.auth.step.AuthStep.로그인_되어_있음;
import static com.bgpark.photoshop.domain.item.step.PictureStep.사진_저장되어_있음;
import static com.bgpark.photoshop.domain.order.step.OrderStep.*;
import static com.bgpark.photoshop.domain.user.step.UserStep.*;

@DisplayName("주문 관련 인수 테스트")
public class OrdersAcceptanceTest extends AcceptanceTest {

    String 이름, 이메일, 비밀번호;
    Set<String> 관심분야;
    AddressRequest 집주소, 회사주소;
    PictureResponse 랜덤사진, 겨울사진, 가을사진;
    OrderItemRequest 랜덤사진_주문, 겨울사진_주문, 가을사진_주문;
    UserResponse 박병길;

    @BeforeEach
    void setUp() throws Exception {
        super.beforeEach();
        이름 = "박병길";
        이메일 = "bgpark82@gmail.com";
        비밀번호 = "password";
        관심분야 = 사용자_관심분야("landscape", "portrait");
        집주소 = 사용자_집주소("서울", "서초구", "201", 12345);
        회사주소 = 사용자_회사주소("서울", "가산로", "롯데아울렛", 12345);
        박병길 = 사용자_생성되어_었음(사용자(이름, 이메일, 비밀번호, 집주소, 회사주소, 관심분야));

        랜덤사진 = 사진_저장되어_있음("bgpark", "http://naver.com", "random", 1000, 100);
        겨울사진 = 사진_저장되어_있음("kassie", "http://google.com", "winter", 15000, 10);
        가을사진 = 사진_저장되어_있음("peter", "http://google.com", "fall", 24000, 90);

        랜덤사진_주문 = 사진_주문(랜덤사진, 3);
        겨울사진_주문 = 사진_주문(겨울사진, 4);
        가을사진_주문 = 사진_주문(가을사진, 5);
    }

    // 주문은 한 종류의 item을 여러개 구입할 수 있다
    @DisplayName("주문을 생성한다")
    @Test
    void create() {
        // given
        String 쿠키 = 로그인_되어_있음(이메일, 비밀번호);

        // when
        ExtractableResponse<Response> response = 주문_생성_요청(쿠키, 랜덤사진_주문, 겨울사진_주문);

        // then
        주문_생성_요청됨(response, 박병길.getId());
    }

    @DisplayName("주문 목록을 조회한다")
    @Test
    void getAll() {
        // given
        String 쿠키 = 로그인_되어_있음(이메일, 비밀번호);
        주문_생성되어_있음(쿠키, 랜덤사진_주문, 겨울사진_주문, 가을사진_주문);
        주문_생성되어_있음(쿠키, 랜덤사진_주문, 겨울사진_주문);

        // when
        ExtractableResponse<Response> response = 주문_조회_요청(쿠키);

        // then
        주문_조회_됨(response);
    }
}
