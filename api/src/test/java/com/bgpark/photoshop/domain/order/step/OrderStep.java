package com.bgpark.photoshop.domain.order.step;

import com.bgpark.photoshop.domain.item.dto.PictureResponse;
import com.bgpark.photoshop.domain.order.domain.DeliveryStatus;
import com.bgpark.photoshop.domain.order.dto.OrderItemRequest;
import com.bgpark.photoshop.domain.order.dto.OrderRequest;
import com.bgpark.photoshop.domain.order.dto.OrderResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.util.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStep {

    private static final String JSESSIONID = "JSESSIONID";
    private static final String ORDER_URL = "/api/v1/orders";

    public static OrderItemRequest 사진_주문(PictureResponse picture, int count) {
        return OrderItemRequest.create(picture.getId(), count);
    }

    public static void 주문_조회_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    public static ExtractableResponse<Response> 주문_조회_요청(String cookie) {
        return RestAssured
                .given().log().all()
                .cookie(JSESSIONID, cookie)
                .when().get(ORDER_URL)
                .then().log().all().extract();
    }

    public static void 주문_생성요청_됨(ExtractableResponse<Response> response, Long userId) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(OrderResponse.class).getUser().getId()).isEqualTo(userId);
        assertThat(response.body().as(OrderResponse.class).getOrderItems().size()).isEqualTo(2);
        assertThat(response.body().as(OrderResponse.class).getDelivery().getStatus()).isEqualTo(DeliveryStatus.READY);
        assertThat(response.body().as(OrderResponse.class).getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(97);
    }

    public static ExtractableResponse<Response> 주문_생성_요청(String cookie, OrderItemRequest... orderItems) {
        OrderRequest request = createOrderRequest(orderItems);
        return RestAssured
                .given().log().all()
                .cookie(JSESSIONID, cookie)
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post(ORDER_URL)
                .then().log().all().extract();
    }

    public static Long 주문_생성되어_있음(String cookie, OrderItemRequest... orderItems) {
        return 주문_생성_요청(cookie, orderItems).as(OrderResponse.class).getId();
    }

    public static OrderRequest createOrderRequest(OrderItemRequest... orderItems) {
        OrderRequest request = new OrderRequest();
        ReflectionTestUtils.setField(request,"orderItems", Lists.newArrayList(orderItems));
        return request;
    }
}
