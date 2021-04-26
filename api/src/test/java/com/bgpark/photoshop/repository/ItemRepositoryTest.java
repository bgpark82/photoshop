package com.bgpark.photoshop.repository;

import com.bgpark.photoshop.config.JpaConfig;
import com.bgpark.photoshop.domain.item.domain.Picture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@MockBean(JpaConfig.class) // 설정을 Mock으로 추가할 수 있었다
class ItemRepositoryTest {

    @Autowired
    private EntityManager em;

    @Test
    void 상속_조인전략_저장() {
        Picture picture = Picture.builder()
                .artist("박병길")
                .name("설원")
                .price(13000)
                .build();

        em.persist(picture);

        assertThat(picture.getArtist()).isEqualTo("박병길");
    }

    @Test
    void 상속_조인전략_조회() {
        Picture picture = Picture.builder()
                .artist("박병길")
                .name("설원")
                .price(13000)
                .build();

        em.persist(picture);

        em.flush();
        em.clear();

        Picture newPicture = em.find(Picture.class, picture.getId());

        System.out.println(newPicture);
        assertThat(newPicture.getArtist()).isEqualTo("박병길");
    }

    @Test
    void 상속_조인전략_날짜조회() {
        Picture picture = Picture.builder()
                .artist("박병길")
                .name("설원")
                .price(13000)
                .build();
        em.persist(picture);

        em.flush();
        em.clear();

        Picture newPicture = em.createQuery("select p from Picture p where p.createdDate > '2021-03-04T11:25:00.698'", Picture.class)
                .getSingleResult();

        // 조회가능한데...?
        System.out.println(newPicture);
        assertThat(newPicture.getCreatedDate()).isBefore(LocalDateTime.now());
    }
}