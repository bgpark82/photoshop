package com.bgpark.photoshop.acceptance;

import com.bgpark.photoshop.AcceptanceTest;
import com.bgpark.photoshop.dto.PictureDto;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.bgpark.photoshop.step.PictureStep.사진_저장요청;
import static org.assertj.core.api.Assertions.assertThat;

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

    private void 사진_저장요청됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.body().as(PictureDto.Res.class).getId()).isEqualTo(1L);
    }
}