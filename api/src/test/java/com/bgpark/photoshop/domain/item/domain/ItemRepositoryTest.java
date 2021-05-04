package com.bgpark.photoshop.domain.item.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.context.annotation.FilterType.ANNOTATION;

@DisplayName("아이템 관련 데이터베이스 테스트")
@DataJpaTest(includeFilters = @ComponentScan.Filter(type = ANNOTATION, classes = EnableJpaAuditing.class)) // @EnableJpaAuditing 어노테이션이 있는 Bean을 호출
class ItemRepositoryTest {

    @Autowired
    EntityManager em;

    @DisplayName("Item을 상속하여 Single Table로 Picture를 저장한다")
    @Test
    void saveInheritanceSingleTable() {
        // given
        Picture picture = 사진_생성();

        // when
        em.persist(picture);

        // then
        assertPicture(picture);
    }

    @DisplayName("Item을 상속하여 Single Table로 Picture를 조회한다")
    @Test
    void findInheritanceSingleTable() {
        // given
        Picture picture = 사진_생성();
        em.persist(picture);
        em.flush();
        em.clear();

        // when
        Picture newPicture = em.find(Picture.class, picture.getId());

        // then
        assertPicture(newPicture);
    }

    @DisplayName("BaseEntity의 날짜를 조회한다")
    @Test
    void 상속_조인전략_날짜조회() {
        // given
        Picture picture = 사진_생성();
        em.persist(picture);
        em.flush();
        em.clear();

        // when
        Picture newPicture = em.createQuery("select p from Picture p where p.createdDate > '2021-03-04T11:25:00.698'", Picture.class)
                .getSingleResult();

        // then
        assertThat(newPicture.getCreatedDate()).isBefore(LocalDateTime.now());
    }

    private Picture 사진_생성() {
        return Picture.entityBuilder()
                .artist("박병길")
                .name("설원")
                .price(13000)
                .build();
    }


    private void assertPicture(Picture picture) {
        assertThat(picture.getArtist()).isEqualTo("박병길");
        assertThat(picture.getName()).isEqualTo("설원");
        assertThat(picture.getPrice()).isEqualTo(13000);
        assertThat(picture.getCreatedDate()).isBefore(LocalDateTime.now());
        assertThat(picture.getModifiedDate()).isBefore(LocalDateTime.now());
    }
}