package com.bgpark.photoshop.domain.user.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("사용자 관련 데이터베이스 테스트")
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private EntityManager em;

    @DisplayName("회원가입을 한다")
    @Test
    void signUp() {
        // given
        User user = User.entityBuilder()
                .name("박병길")
                .build();

        // when
        em.persist(user);

        // then
        assertThat(user.getName()).isEqualTo("박병길");
    }

    @DisplayName("회원가입 시, 집 주소를 추가한다")
    @Test
    void signUpAddHomeAddress() {
        // given
        Address homeAddress = createHomeAddress();
        User user = User.entityBuilder()
                .name("박병길")
                .homeAddress(homeAddress)
                .build();

        // when
        em.persist(user);

        // then
        assertThat(user.getHomeAddress().getCity()).isEqualTo("서울시");
        assertThat(user.getWorkAddress()).isNull();
    }

    @DisplayName("회원가입 시, 회사 주소를 추가한다")
    @Test
    void signUpAddWorkAddress() {
        // given
        Address workAddress = createWorkAddress();
        User user = User.entityBuilder()
                .name("박병길")
                .workAddress(workAddress)
                .build();

        //when
        em.persist(user);

        // then
        assertThat(user.getWorkAddress().getStreet()).isEqualTo("가산동");
        assertThat(user.getHomeAddress()).isNull();
    }


    @DisplayName("회원가입 시, 좋아하는 사진을 추가한다")
    @Test
    void signUpAddFavorites() {
        // given
        HashSet<String> favorites = createFavorites();
        User user = User.entityBuilder()
                .name("박병길")
                .favorites(favorites)
                .build();

        // when
        em.persist(user);

        // then
        assertThat(user.getFavorites()).contains("portrait");
    }

    @DisplayName("회원가입 시, 집 주소 히스토리를 확인한다")
    @Test
    void signUpCheckHomeAddressHistory() {
        // given
        Address homeAddress = createHomeAddress();
        User user = User.entityBuilder()
                .name("박병길")
                .homeAddress(homeAddress)
                .build();

        // when
        em.persist(user);

        user.updateHomeAddress("김해시","동상동","아파트",111);
        em.flush();
        em.clear();

        user.getFavorites();

        // then
        assertThat(user.getHomeAddress().getCity()).isEqualTo("김해시");
    }

    @DisplayName("회원의 집 주소 히스토리를 확인한다")
    @Test
    void findHomeAddressHistory() {
        // given
        HashSet<String> favorites = createFavorites();
        Address homeAddress = createHomeAddress();
        User user = User.entityBuilder()
                .name("박병길")
                .favorites(favorites)
                .homeAddress(homeAddress)
                .build();

        // when
        em.persist(user);
        em.flush();
        em.clear();

        User find = em.find(User.class, user.getId());

        // 기본적으로 @ElementCollection은 지연로딩 사용
        assertThat(find.getName()).isEqualTo("박병길");
        assertThat(find.getFavorites()).contains("portrait");
    }

    @DisplayName("회원의 좋아하는 사진을 변경한다")
    @Test
    void updateFavorites() {
        // given
        HashSet<String> favorites = createFavorites();
        User user = User.entityBuilder()
                .name("박병길")
                .favorites(favorites)
                .build();
        em.persist(user);
        em.flush();
        em.clear();

        // when
        user.switchFavorites("portrait","wallpaper");

        // then
        assertThat(user.getFavorites().contains("portrait")).isFalse();
        assertThat(user.getFavorites().contains("wallpaper")).isTrue();
    }

    @DisplayName("회원의 주소 히스토리를 변경한다")
    @Test
    void updateHomeAddressHistory() {
        // given
        Address homeAddress = createHomeAddress();
        User user = User.entityBuilder()
                .name("박병길")
                .homeAddress(homeAddress)
                .build();

        // when
        em.persist(user);
        em.flush();
        em.clear();

        // given
        Address newAddress = Address.entityBuilder()
                .city("서울시")
                .street("가산동")
                .detail("백화점단지")
                .zipcode(12345)
                .build();

        // when
        user.switchAddress(homeAddress, newAddress);
        em.flush();
        em.clear();

        // then
        assertThat(user.getHomeAddressHistory().contains(homeAddress)).isFalse();
        assertThat(user.getHomeAddressHistory().contains(newAddress)).isTrue();
    }

    @Test
    void unmodifiableList_테스트() {
        List<Integer> numbers = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
        assertThatThrownBy(() -> numbers.add(4));
    }

    private Address createWorkAddress() {
        return Address.entityBuilder()
                .city("서울시")
                .street("가산동")
                .detail("롯데백화점")
                .zipcode(51534)
                .build();
    }

    private Address createHomeAddress() {
        return Address.entityBuilder()
                .city("서울시")
                .street("서초대로")
                .detail("201")
                .zipcode(50412)
                .build();
    }

    private HashSet<String> createFavorites() {
        HashSet<String> favorites = new HashSet<String>();
        favorites.add("portrait");
        favorites.add("landscape");
        return favorites;
    }
}