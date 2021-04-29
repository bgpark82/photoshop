package com.bgpark.photoshop.domain.place.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.place.dto.PlaceRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.bgpark.photoshop.domain.place.step.PlaceStep.장소_저장_요청;
import static com.bgpark.photoshop.domain.place.step.PlaceStep.장소_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 관련 인수테스트")
public class PlaceAcceptanceTest extends AcceptanceTest {

    PlaceRequest 남산;

    @BeforeEach
    void setUp() {
        남산 = 장소_생성("남산", 37.5537747, 126.9722148);
    }

    @DisplayName("장소를 저장한다")
    @Test
    void save() {
        // when
        ExtractableResponse<Response> response = 장소_저장_요청(남산);

        // then
        장소_저장_됨(response);
    }

    private void 장소_저장_됨(ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.header("location")).isEqualTo("/place/1");
    }
}
