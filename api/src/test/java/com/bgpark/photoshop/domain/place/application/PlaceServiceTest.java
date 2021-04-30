package com.bgpark.photoshop.domain.place.application;

import com.bgpark.photoshop.domain.place.domain.Place;
import com.bgpark.photoshop.domain.place.domain.PlaceRepository;
import com.bgpark.photoshop.domain.place.dto.PlaceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.bgpark.photoshop.domain.place.step.PlaceStep.장소_엔티티_생성;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@DisplayName("장소 관련 서비스 테스트")
@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    PlaceService placeService;
    @Mock PlaceRepository placeRepository;

    @BeforeEach
    void setUp() {
        placeService = new PlaceService(placeRepository);
    }

    @DisplayName("장소를 키워드로 조회한다")
    @Test
    void findByKeyword() {
        // given
        String keyword = "남";
        List<Place> places = newArrayList(
                장소_엔티티_생성(1L, "남산", 37.5537747, 126.9722148),
                장소_엔티티_생성(2L, "남포동", 35.0963437, 129.0287312));
        when(placeRepository.findByNameStartsWith(keyword)).thenReturn(places);

        // when
        List<PlaceResponse> response = placeService.findByKeyword(keyword);

        // then
        assertThat(response).containsExactly(
                createPlaceResponse(1L, "남산", 37.5537747, 126.9722148),
                createPlaceResponse(2L, "남포동", 35.0963437, 129.0287312));
    }

    private PlaceResponse createPlaceResponse(long placeId, String name, double lat, double lng) {
        PlaceResponse response = new PlaceResponse();
        setField(response, "id", placeId);
        setField(response, "name", name);
        setField(response, "lat", lat);
        setField(response, "lng", lng);
        return response;
    }
}