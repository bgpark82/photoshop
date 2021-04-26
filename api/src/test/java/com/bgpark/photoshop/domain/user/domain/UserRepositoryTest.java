package com.bgpark.photoshop.domain.user.domain;

import com.bgpark.photoshop.domain.user.domain.Address;
import com.bgpark.photoshop.domain.user.domain.User;
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

@DataJpaTest
@Transactional
class UserRepositoryTest {

    @Autowired
    private EntityManager em;

    @Test
    void 회원가입() {
        User user = User.builder()
                .name("박병길")
                .build();

        em.persist(user);

        assertThat(user.getName()).isEqualTo("박병길");
    }

    @Test
    void 회원가입_집주소추가() {
        Address homeAddress = createHomeAddress();
        User user = User.builder()
                .name("박병길")
                .homeAddress(homeAddress)
                .build();

        em.persist(user);

        System.out.println(user);
        assertThat(user.getHomeAddress().getCity()).isEqualTo("서울시");
        assertThat(user.getWorkAddress()).isNull();
    }



    @Test
    void 회원가입_회사주소추가() {
        Address workAddress = createWorkAddress();

        User user = User.builder()
                .name("박병길")
                .workAddress(workAddress)
                .build();

        em.persist(user);

        System.out.println(user);
        assertThat(user.getWorkAddress().getStreet()).isEqualTo("가산동");
        assertThat(user.getHomeAddress()).isNull();
    }



    @Test
    void 회원가입_좋아하는사진추가() {
        HashSet<String> favorites = createFavorites();

        User user = User.builder()
                .name("박병길")
                .favorites(favorites)
                .build();

        em.persist(user);

        System.out.println(user);
        assertThat(user.getFavorites()).contains("portrait");
    }



    @Test
    void 회원가입_주소히스토리() {
        Address homeAddress = createHomeAddress();
        User user = User.builder()
                .name("박병길")
                .homeAddress(homeAddress)
                .build();

        em.persist(user);

        user.updateHomeAddress("김해시","동상동","아파트",111);

        em.flush();
        em.clear();

        user.getFavorites();

        assertThat(user.getHomeAddress().getCity()).isEqualTo("김해시");
    }

    @Test
    void 회원조회_주소히스토리() {
        HashSet<String> favorites = createFavorites();
        Address homeAddress = createHomeAddress();
        User user = User.builder()
                .name("박병길")
                .favorites(favorites)
                .homeAddress(homeAddress)
                .build();
        em.persist(user);

        em.flush();
        em.clear();

        User find = em.find(User.class, user.getId());

        // 기본적으로 @ElementCollection은 지연로딩 사용
        assertThat(find.getName()).isEqualTo("박병길");
        assertThat(find.getFavorites()).contains("portrait");
    }

    @Test
    void 회원변경_좋아하는사진변경() {
        HashSet<String> favorites = createFavorites();
        User user = User.builder()
                .name("박병길")
                .favorites(favorites)
                .build();
        em.persist(user);
        em.flush();
        em.clear();

        user.switchFavorites("portrait","wallpaper");

        assertThat(user.getFavorites().contains("portrait")).isFalse();
        assertThat(user.getFavorites().contains("wallpaper")).isTrue();
    }


    @Test
    void 회원변경_주소히스토리변경() {
        Address homeAddress = createHomeAddress();
        User user = User.builder()
                .name("박병길")
                .homeAddress(homeAddress)
                .build();
        em.persist(user);

        em.flush();
        em.clear();

        Address newAddress = Address.builder()
                .city("서울시")
                .street("가산동")
                .detail("백화점단지")
                .zipcode(12345)
                .build();

        user.switchAddress(homeAddress, newAddress);

        em.flush();
        em.clear();

        System.out.println(user);
        assertThat(user.getHomeAddressHistory().contains(homeAddress)).isFalse();
        assertThat(user.getHomeAddressHistory().contains(newAddress)).isTrue();
    }

    @Test
    void unmodifiableList_테스트() {
        List<Integer> numbers = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
        assertThatThrownBy(() -> numbers.add(4));
    }

    private Address createWorkAddress() {
        return Address.builder()
                .city("서울시")
                .street("가산동")
                .detail("롯데백화점")
                .zipcode(51534)
                .build();
    }

    private Address createHomeAddress() {
        return Address.builder()
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