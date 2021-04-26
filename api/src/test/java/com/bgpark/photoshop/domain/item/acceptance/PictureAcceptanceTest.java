package com.bgpark.photoshop.domain.item.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bgpark.photoshop.domain.item.step.PictureStep.사진_저장요청;
import static com.bgpark.photoshop.domain.item.step.PictureStep.사진_저장요청됨;

@DisplayName("사진 관련 인수테스트")
class PictureAcceptanceTest extends AcceptanceTest {

    @BeforeEach
    void setUp() throws Exception {
        super.beforeEach();
    }

    @DisplayName("사진을 저장한다")
    @Test
    void save() {
        // when
        ExtractableResponse<Response> response = 사진_저장요청();

        // then
        사진_저장요청됨(response);
    }
}