package com.bgpark.photoshop.domain.user.dto;

import com.bgpark.photoshop.domain.user.domain.Address;
import com.bgpark.photoshop.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

public class UserDto {


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {
        private String name;
        private String email;
        private String password;
        private AddressDto.SaveReq homeAddress;
        private AddressDto.SaveReq workAddress;
        private Set<String> favorites;

        public User toEntity() {
            Address homeAddress = this.homeAddress == null ? null : this.homeAddress.toEntity();
            Address workAddress = this.homeAddress == null ? null : this.workAddress.toEntity();

            return User.builder()
                    .email(email)
                    .password(password)
                    .homeAddress(homeAddress)
                    .workAddress(workAddress)
                    .favorites(favorites)
                    .name(name)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class Res {
        private Long id;
        private String name;
        private String email;
        private String password;
        private Address homeAddress;
        private Address workAddress;
        private Set<String> favorites;
        private List<Address> homeAddressHistory;

        @Builder
        private Res(Long id, String name, String email, String password, Address homeAddress, Address workAddress, Set<String> favorites, List<Address> homeAddressHistory) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.homeAddress = homeAddress;
            this.workAddress = workAddress;
            this.favorites = favorites;
            this.homeAddressHistory = homeAddressHistory;
        }

        public static Res of(User user){
            return Res.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .favorites(user.getFavorites())
                    .homeAddress(user.getHomeAddress())
                    .workAddress(user.getWorkAddress())
                    .name(user.getName())
                    .homeAddressHistory(user.getHomeAddressHistory())
                    .id(user.getId())
                    .build();
        }
    }



}
