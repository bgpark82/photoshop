package com.bgpark.photoshop.domain.user.documentation;

import com.bgpark.photoshop.common.Documentation;
import com.bgpark.photoshop.domain.user.dto.AddressRequest;
import com.bgpark.photoshop.domain.user.dto.UserRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.bgpark.photoshop.domain.user.step.UserStep.*;
import static com.bgpark.photoshop.domain.user.step.UserStep.사용자;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class UserDocumentationTest extends Documentation {

    @Test
    void create() {
        // given
        String 이름 = "박병길";
        String 이메일 = "bgpark82@gmail.com";
        String 비밀번호 = "password";
        AddressRequest 사용자_집주소 = 사용자_집주소("서울", "가산동", "롯데백화점", 2468);
        AddressRequest 사용자_회사주소 = 사용자_회사주소("서울", "가산동", "롯데백화점", 2468);
        Set<String> 사용자_관심분야 = 사용자_관심분야("portrait", "landscape");

        UserRequest 사용자 = 사용자(이름, 이메일, 비밀번호, 사용자_집주소, 사용자_회사주소, 사용자_관심분야);

        // when
        RestAssured
                .given(spec).log().all()
                .filter(document("user",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(사용자)
                .when().post("/api/v1/users")
                .then().log().all().extract();
    }
}
