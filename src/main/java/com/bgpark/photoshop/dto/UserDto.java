package com.bgpark.photoshop.dto;

import com.bgpark.photoshop.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

public class UserDto {


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SaveReq {
        private String name;
        private AddressDto.SaveReq homeAddress;
        private AddressDto.SaveReq workAddress;
        private Set<String> favorites;

        public User toEntity() {
            return User.builder()
                    .homeAddress(homeAddress.toEntity())
                    .workAddress(workAddress.toEntity())
                    .favorites(favorites)
                    .name(name)
                    .build();
        }
    }

}
