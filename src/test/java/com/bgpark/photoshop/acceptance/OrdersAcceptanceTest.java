package com.bgpark.photoshop.acceptance;

import com.bgpark.photoshop.AcceptanceTest;
import com.bgpark.photoshop.domain.DeliveryStatus;
import com.bgpark.photoshop.dto.AddressDto;
import com.bgpark.photoshop.dto.OrderDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.bgpark.photoshop.step.PictureStep.사진;
import static com.bgpark.photoshop.step.PictureStep.사진_저장되어_있음;
import static com.bgpark.photoshop.step.UserStep.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("주문 관련 인수테스트")
public class OrdersAcceptanceTest extends AcceptanceTest {

    Long userId, pictureId;

    @BeforeEach
    void setUp() throws Exception {
        super.beforeEach();
        Set<String> 관심분야 = 사용자_관심분야("landscape", "portrait");
        AddressDto.SaveReq 집주소 = 사용자_집주소("서울", "서초구", "201", 12345);
        AddressDto.SaveReq 회사주소 = 사용자_회사주소("서울", "가산로", "롯데아울렛", 12345);

        userId = 사용자_생성요청되었음(사용자("박병길", 집주소, 회사주소, 관심분야));
        pictureId = 사진_저장되어_있음(사진("bgpark", "http://google.com", "random", 1000));
    }

    /** 주문은 한 종류의 item을 여러개 구입할 수 있다 */
    @DisplayName("주문을 생성한다")
    @Test
    void create() {
        // given
        OrderDto.Req request = new OrderDto.Req(userId, pictureId, 3);

        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/orders")
                .then().log().all().extract();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(OrderDto.Res.class).getUser().getId()).isEqualTo(userId);
        assertThat(response.body().as(OrderDto.Res.class).getPictures().size()).isEqualTo(1);
        assertThat(response.body().as(OrderDto.Res.class).getDelivery().getStatus()).isEqualTo(DeliveryStatus.READY);
    }
}
