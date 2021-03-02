package com.bgpark.photoshop.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 엔티티 내부에서만 객체 생성하도록 제한
@AllArgsConstructor
@Getter
@ToString
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "HOME_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "HOME_STREET")),
            @AttributeOverride(name = "detail", column = @Column(name = "HOME_DETAIL")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "HOME_ZIPCODE")),
    })
    private Address homeAddress = new Address();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "detail", column = @Column(name = "WORK_DETAIL")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "WORK_ZIPCODE")),
    })
    private Address workAddress = new Address();

    @ElementCollection
    @CollectionTable(name = "FAVORITE_THEME", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "FAVORITES")
    private Set<String> favorites = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "HOME_ADDRESS_HISTORY", joinColumns = @JoinColumn(name = "USER_ID"))
    private List<Address> homeAddressHistory = new ArrayList<>();

    public void updateHomeAddress(final String city, final String street, final String detail, final int zipcode) {
        final Address homeAddress = Address.builder()
                .city(city)
                .street(street)
                .detail(detail)
                .zipcode(zipcode)
                .build();

        this.homeAddress = homeAddress;
        this.homeAddressHistory.add(homeAddress);
    }

    @Builder
    public User(String name, Address homeAddress, Address workAddress, Set<String> favorites) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.favorites = favorites;
        this.homeAddressHistory.add(homeAddress);
    }

    public void switchFavorites(String origin, String target) {
        this.favorites.remove(origin);
        this.favorites.add(target);
    }

    public void switchAddress(Address homeAddress, Address target) {
        this.homeAddressHistory.remove(homeAddress);
        this.homeAddressHistory.add(target);
    }
}
