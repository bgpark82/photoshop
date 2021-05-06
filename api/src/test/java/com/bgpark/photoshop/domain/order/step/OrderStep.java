package com.bgpark.photoshop.domain.order.step;

import com.bgpark.photoshop.domain.item.dto.PictureResponse;
import com.bgpark.photoshop.domain.order.domain.DeliveryStatus;
import com.bgpark.photoshop.domain.order.dto.OrderItemRequest;
import com.bgpark.photoshop.domain.order.dto.OrderRequest;
import com.bgpark.photoshop.domain.order.dto.OrderResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStep {

    public static OrderItemRequest 사진_주문(PictureResponse picture, int count) {
        return OrderItemRequest.create(picture.getId(), count);
    }

    public static void 주문_조회됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static ExtractableResponse<Response> 주문_조회_요청() {
        return RestAssured
                .given().log().all()
                .when().get("/api/v1/orders")
                .then().log().all().extract();
    }

    public static void 주문_생성_요청됨(ExtractableResponse<Response> response, Long userId) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(OrderResponse.class).getUser().getId()).isEqualTo(userId);
        assertThat(response.body().as(OrderResponse.class).getOrderItems().size()).isEqualTo(2);
        assertThat(response.body().as(OrderResponse.class).getDelivery().getStatus()).isEqualTo(DeliveryStatus.READY);
        assertThat(response.body().as(OrderResponse.class).getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(97);
    }

    public static ExtractableResponse<Response> 주문_생성_요청(String cookie, OrderItemRequest... orderItems) {
        OrderRequest request = OrderRequest.create(orderItems);
        return RestAssured
                .given().log().all()
                .cookie("JSESSIONID", cookie)
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/orders")
                .then().log().all().extract();
    }

    public static Long 주문_생성되어_있음(String cookie, OrderItemRequest... orderItems) {
        return 주문_생성_요청(cookie, orderItems).as(OrderResponse.class).getId();
    }
}
