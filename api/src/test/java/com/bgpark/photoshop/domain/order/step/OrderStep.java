package com.bgpark.photoshop.domain.order.step;

import com.bgpark.photoshop.domain.order.domain.DeliveryStatus;
import com.bgpark.photoshop.domain.order.dto.OrderDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStep {

    public static void 주문_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        //assertThat(response.body().as(new TypeRef<OrderDto.Res>() {}).
    }

    public static ExtractableResponse<Response> 주문_조회_요청() {
        return RestAssured
                .given().log().all()
                .when().get("/api/v1/orders")
                .then().log().all().extract();
    }

    public static void 주문_생성_요청됨(ExtractableResponse<Response> response, Long userId) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(OrderDto.Res.class).getUser().getId()).isEqualTo(userId);
        assertThat(response.body().as(OrderDto.Res.class).getOrderItems().size()).isEqualTo(2);
        assertThat(response.body().as(OrderDto.Res.class).getDelivery().getStatus()).isEqualTo(DeliveryStatus.READY);
        assertThat(response.body().as(OrderDto.Res.class).getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(97);
    }

    public static ExtractableResponse<Response> 주문_생성_요청(OrderDto.Req request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/orders")
                .then().log().all().extract();
    }

    public static Long 주문_생성_요청_되어있음(OrderDto.Req request) {
        return 주문_생성_요청(request).as(OrderDto.Res.class).getId();
    }
}
