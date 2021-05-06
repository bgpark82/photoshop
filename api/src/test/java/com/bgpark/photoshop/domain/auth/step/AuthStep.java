package com.bgpark.photoshop.domain.auth.step;

import com.bgpark.photoshop.domain.auth.dto.AuthRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthStep {

    public static void 로그인_요청_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.cookie("JSESSIONID")).isNotNull();
    }

    public static AuthRequest 로그인_정보_생성(String email, String password) {
        return AuthRequest.create(email, password);
    }

    public static ExtractableResponse<Response> 로그인_요청(String email, String password) {
        AuthRequest request = 로그인_정보_생성(email, password);
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/login")
                .then().log().all().extract();
    }

    public static String 로그인_되어_있음(String email, String password) {
        return 로그인_요청(email, password).cookie("JSESSIONID");
    }
}
