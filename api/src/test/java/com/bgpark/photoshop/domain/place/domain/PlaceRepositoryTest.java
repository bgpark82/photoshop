package com.bgpark.photoshop.domain.place.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.bgpark.photoshop.domain.place.step.PlaceStep.장소_엔티티_생성;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("장소 관련 데이터베이스 테스트")
@DataJpaTest
class PlaceRepositoryTest {

    @Autowired
    PlaceRepository placeRepository;

    @DisplayName("장소를 키워드로 조회한다")
    @Test
    void findByNameEndsWith() {
        // given
        placeRepository.save(Place.create("남산", 37.5537747, 126.9722148));
        placeRepository.save(Place.create("남포동", 35.0963437, 129.0287312));
        placeRepository.save(Place.create( "경복궁", 37.5796212, 126.974847));

        // when
        List<Place> places = placeRepository.findByNameEndsWith("남");

        // then
        assertThat(places).containsExactly(
                장소_엔티티_생성(1L, "남산", 37.5537747, 126.9722148),
                장소_엔티티_생성(2L, "남포동", 35.0963437, 129.0287312));
    }
}