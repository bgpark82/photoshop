package com.bgpark.photoshop.domain.user.step;

import com.bgpark.photoshop.domain.user.dto.AddressRequest;
import com.bgpark.photoshop.domain.user.dto.UserRequest;
import com.bgpark.photoshop.domain.user.dto.UserResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class UserStep {

    public static UserRequest 사용자(String name, String email, String password, AddressRequest homeAddressReq, AddressRequest workAddressReq, Set<String> favoritesReq) {
        return UserRequest.create(name, email, password, homeAddressReq, workAddressReq, favoritesReq);
    }

    public static UserRequest 사용자_생성(String name, String email, String password) {
        UserRequest request = new UserRequest();
        ReflectionTestUtils.setField(request, "name", name);
        ReflectionTestUtils.setField(request, "email", email);
        ReflectionTestUtils.setField(request, "password", password);
        return request;
    }

    public static Set<String> 사용자_관심분야(String ...favorite) {
        return Arrays.stream(favorite)
                .collect(Collectors.toSet());
    }

    public static AddressRequest 사용자_회사주소(String city, String street, String detail, int zipcode) {
        return AddressRequest.create(city, street, detail, zipcode);
    }

    public static AddressRequest 사용자_집주소(String city, String street, String detail, int zipcode) {
        return AddressRequest.create(city, street, detail, zipcode);
    }

    public static UserResponse 사용자_생성되어_었음(UserRequest request) {
        return 사용자_생성_요청(request).as(UserResponse.class);
    }

    public static ExtractableResponse<Response> 사용자_생성_요청(UserRequest request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/users")
                .then().log().all().extract();
    }

    public static UserResponse 사용자_생성되어_있음(UserRequest request) {
        return 사용자_생성_요청(request).as(UserResponse.class);
    }

    public static void 사용자_생성_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(UserResponse.class).getId()).isEqualTo(1L);
        assertThat(response.body().as(UserResponse.class).getEmail()).isEqualTo("bgpark82@gmail.com");
        assertThat(response.body().as(UserResponse.class).getPassword()).isEqualTo("password");
    }

    public static void 사용자_조회_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.as(UserResponse.class).getEmail()).isEqualTo("bgpark82@gmail.com");
        assertThat(response.as(UserResponse.class).getName()).isEqualTo("박병길");
    }

    public static ExtractableResponse<Response> 사용자_조회_요청(String cookie) {
        return RestAssured
                .given().log().all()
                .cookie("JSESSIONID", cookie)
                .when().get("/api/v1/users/me")
                .then().log().all().extract();
    }
}
