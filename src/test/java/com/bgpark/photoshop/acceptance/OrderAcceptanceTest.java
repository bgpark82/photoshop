package com.bgpark.photoshop.acceptance;

import com.bgpark.photoshop.AcceptanceTest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@DisplayName("주문 관련 인수테스트")
public class OrderAcceptanceTest extends AcceptanceTest {

    @DisplayName("주문을 생성한다")
    @Test
    void create() {

        // when
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .body("")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/orders")
                .then().log().all().extract();

        // then
        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }
}
