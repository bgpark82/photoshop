package com.bgpark.photoshop.domain.order.documentation;

import com.bgpark.photoshop.common.Documentation;
import com.bgpark.photoshop.domain.item.dto.PictureResponse;
import com.bgpark.photoshop.domain.order.dto.OrderItemRequest;
import com.bgpark.photoshop.domain.user.dto.AddressRequest;
import com.bgpark.photoshop.domain.user.dto.UserResponse;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Set;

import static com.bgpark.photoshop.domain.auth.step.AuthStep.로그인_되어_있음;
import static com.bgpark.photoshop.domain.item.step.PictureStep.사진_저장되어_있음;
import static com.bgpark.photoshop.domain.order.step.OrderStep.*;
import static com.bgpark.photoshop.domain.user.step.UserStep.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

@DisplayName("주문 관련 문서테스트")
public class OrderDocumentationTest extends Documentation {

    String 이름, 이메일, 비밀번호;
    Set<String> 관심분야;
    AddressRequest 집주소, 회사주소;
    PictureResponse 랜덤사진, 겨울사진, 가을사진;
    OrderItemRequest 랜덤사진_주문, 겨울사진_주문, 가을사진_주문;
    UserResponse 박병길;

    @BeforeEach
    void setUp() throws Exception {
        이름 = "박병길";
        이메일 = "bgpark82@gmail.com";
        비밀번호 = "password";
        관심분야 = 사용자_관심분야("landscape", "portrait");
        집주소 = 사용자_집주소("서울", "서초구", "201", 12345);
        회사주소 = 사용자_회사주소("서울", "가산로", "롯데아울렛", 12345);
        박병길 = 사용자_생성되어_있음(사용자(이름, 이메일, 비밀번호, 집주소, 회사주소, 관심분야));

        랜덤사진 = 사진_저장되어_있음("bgpark", "http://naver.com", "random", 1000, 100);
        겨울사진 = 사진_저장되어_있음("kassie", "http://google.com", "winter", 15000, 10);
        가을사진 = 사진_저장되어_있음("peter", "http://google.com", "fall", 24000, 90);

        랜덤사진_주문 = 사진_주문(랜덤사진, 3);
        겨울사진_주문 = 사진_주문(겨울사진, 4);
        가을사진_주문 = 사진_주문(가을사진, 5);
    }

    // 주문은 한 종류의 item을 여러개 구입할 수 있다
    @DisplayName("주문을 생성한다")
    @Test
    void create() {
        // given
        String 쿠키 = 로그인_되어_있음(이메일, 비밀번호);

        // when
        ExtractableResponse<Response> response = RestAssured
                .given(spec).log().all()
                .filter(document("order",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .cookie("JSESSIONID", 쿠키)
                .body(createOrderRequest(랜덤사진_주문, 겨울사진_주문))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/api/v1/orders")
                .then().log().all().extract();

        // then
        주문_생성요청_됨(response, 박병길.getId());
    }

}
