package com.bgpark.photoshop.domain.user.step;

import com.bgpark.photoshop.domain.user.dto.AddressRequest;
import com.bgpark.photoshop.domain.user.dto.UserDto;
import com.bgpark.photoshop.domain.user.dto.UserResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class UserStep {

    public static UserDto.Req 사용자(String name, String email, String password, AddressRequest homeAddressReq, AddressRequest workAddressReq, Set<String> favoritesReq) {
        return UserDto.Req
                .builder()
                .email(email)
                .password(password)
                .homeAddress(homeAddressReq)
                .workAddress(workAddressReq)
                .favorites(favoritesReq)
                .name(name)
                .build();
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

    public static UserResponse 사용자_생성되어_었음(UserDto.Req request) {
        return 사용자_생성_요청(request).as(UserResponse.class);
    }

    public static ExtractableResponse<Response> 사용자_생성_요청(UserDto.Req request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/users")
                .then().log().all().extract();
    }

    public static UserDto.Res 사용자_생성되어_있음(UserDto.Req request) {
        return 사용자_생성_요청(request).as(UserDto.Res.class);
    }

    public static void 사용자_생성_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(UserDto.Res.class).getId()).isEqualTo(1L);
        assertThat(response.body().as(UserDto.Res.class).getEmail()).isEqualTo("bgpark82@gmail.com");
        assertThat(response.body().as(UserDto.Res.class).getPassword()).isEqualTo("password");
    }

    public static void 사용자_조회_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.as(UserDto.Res.class).getEmail()).isEqualTo("bgpark82@gmail.com");
        assertThat(response.as(UserDto.Res.class).getName()).isEqualTo("박병길");
    }

    public static ExtractableResponse<Response> 사용자_조회_요청(String cookie) {
        return RestAssured
                .given().log().all()
                .cookie("JSESSIONID", cookie)
                .when().get("/api/v1/users/me")
                .then().log().all().extract();
    }
}
