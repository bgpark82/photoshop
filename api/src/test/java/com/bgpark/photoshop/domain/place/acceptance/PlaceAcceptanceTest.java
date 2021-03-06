package com.bgpark.photoshop.domain.place.acceptance;

import com.bgpark.photoshop.common.AcceptanceTest;
import com.bgpark.photoshop.domain.place.dto.PlaceRequest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bgpark.photoshop.domain.place.step.PlaceStep.*;

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

    @DisplayName("장소를 키워드로 조회한다")
    @Test
    void findByKeyword() {
        // given
        장소_저장되어_있음("남산", 37.5537747, 126.9722148);
        장소_저장되어_있음("남포동", 35.0963437,129.0287312);
        장소_저장되어_있음("경복궁", 37.5796212,126.974847);
        String keyword = "남";

        // when
        ExtractableResponse<Response> response = 장소_키워드_조회_요청(keyword);

        // then
        장소_키워드_조회_됨(response);
    }
}
