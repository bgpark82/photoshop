package com.bgpark.photoshop.acceptance;

import com.bgpark.photoshop.AcceptanceTest;
import com.bgpark.photoshop.domain.user.dto.AddressDto;
import com.bgpark.photoshop.domain.order.dto.OrderDto;
import com.bgpark.photoshop.domain.order.dto.OrderItemDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.bgpark.photoshop.step.OrderStep.*;
import static com.bgpark.photoshop.step.PictureStep.사진;
import static com.bgpark.photoshop.step.PictureStep.사진_저장되어_있음;
import static com.bgpark.photoshop.step.UserStep.*;

@DisplayName("주문 관련 인수테스트")
public class OrdersAcceptanceTest extends AcceptanceTest {

    Long userId, pictureId1, pictureId2, pictureId3;
    OrderItemDto.Req 아이템_주문1, 아이템_주문2, 아이템_주문3;

    @BeforeEach
    void setUp() throws Exception {
        super.beforeEach();
        Set<String> 관심분야 = 사용자_관심분야("landscape", "portrait");
        AddressDto.SaveReq 집주소 = 사용자_집주소("서울", "서초구", "201", 12345);
        AddressDto.SaveReq 회사주소 = 사용자_회사주소("서울", "가산로", "롯데아울렛", 12345);

        userId = 사용자_생성요청되었음(사용자("박병길", 집주소, 회사주소, 관심분야));
        pictureId1 = 사진_저장되어_있음(사진("bgpark", "http://naver.com", "random", 1000, 100));
        pictureId2 = 사진_저장되어_있음(사진("kassie", "http://google.com", "winter", 15000, 10));
        pictureId3 = 사진_저장되어_있음(사진("peter", "http://google.com", "fall", 24000, 90));
        아이템_주문1 = new OrderItemDto.Req(pictureId1, 3);
        아이템_주문2 = new OrderItemDto.Req(pictureId2, 4);
        아이템_주문3 = new OrderItemDto.Req(pictureId3, 5);
    }

    /** 주문은 한 종류의 item을 여러개 구입할 수 있다 */
    @DisplayName("주문을 생성한다")
    @Test
    void create() {
        // when
        ExtractableResponse<Response> response = 주문_생성_요청(OrderDto.Req.of(userId, 아이템_주문1, 아이템_주문2));

        // then
        주문_생성_요청됨(response, userId);
    }

    @DisplayName("주문 목록을 조회한다")
    @Test
    void getAll() {
        // given
        주문_생성_요청_되어있음(OrderDto.Req.of(userId, 아이템_주문1, 아이템_주문2, 아이템_주문3));
        주문_생성_요청_되어있음(OrderDto.Req.of(userId, 아이템_주문1, 아이템_주문2));

        // when
        ExtractableResponse<Response> response = 주문_조회_요청();

        // then
        주문_조회됨(response);
    }
}
