package com.bgpark.photoshop.domain.user.step;

import com.bgpark.photoshop.domain.user.dto.AddressDto;
import com.bgpark.photoshop.domain.user.dto.UserDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UserStep {

    public static UserDto.Req 사용자(String name, AddressDto.SaveReq homeAddressReq, AddressDto.SaveReq workAddressReq, Set<String> favoritesReq) {
        return UserDto.Req
                .builder()
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

    public static AddressDto.SaveReq 사용자_회사주소(String city, String street, String detail, int zipcode) {
        return AddressDto.SaveReq
                .builder()
                .city(city)
                .street(street)
                .detail(detail)
                .zipcode(zipcode)
                .build();
    }

    public static AddressDto.SaveReq 사용자_집주소(String city, String street, String detail, int zipcode) {
        return AddressDto.SaveReq
                .builder()
                .city(city)
                .street(street)
                .detail(detail)
                .zipcode(zipcode)
                .build();
    }

    public static Long 사용자_생성요청되었음(UserDto.Req request) {
        return 사용자_생성요청(request).as(UserDto.Res.class).getId();
    }

    public static ExtractableResponse<Response> 사용자_생성요청(UserDto.Req request) {
        return RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/v1/users")
                .then().log().all().extract();
    }
}
