package com.bgpark.photoshop.domain.item.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bgpark.photoshop.domain.item.step.PictureStep.*;

@DisplayName("사진 관련 인수테스트")
class PictureAcceptanceTest extends AcceptanceTest {

    @DisplayName("사진을 저장한다")
    @Test
    void save() {
        // when
        ExtractableResponse<Response> response = 사진_저장_요청("bgpark", "www.google.com", "night owl", 1000, 100);

        // then
        사진_저장요청_됨(response);
    }
}