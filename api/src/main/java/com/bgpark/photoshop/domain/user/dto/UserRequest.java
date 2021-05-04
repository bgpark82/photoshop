package com.bgpark.photoshop.domain.user.dto;

import com.bgpark.photoshop.domain.user.domain.Address;
import com.bgpark.photoshop.domain.user.domain.User;
import lombok.Getter;

import java.util.Set;

@Getter
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private AddressRequest homeAddress;
    private AddressRequest workAddress;
    private Set<String> favorites;

    public static UserRequest create(String name, String email, String password, AddressRequest homeAddress, AddressRequest workAddress, Set<String> favorites) {
        UserRequest request = new UserRequest();
        request.name = name;
        request.email = email;
        request.password = password;
        request.homeAddress = homeAddress;
        request.workAddress = workAddress;
        request.favorites = favorites;
        return request;
    }

    public User toEntity() {
        Address homeAddress = this.homeAddress == null ? null : this.homeAddress.toEntity();
        Address workAddress = this.homeAddress == null ? null : this.workAddress.toEntity();

        return User.entityBuilder()
                .email(email)
                .password(password)
                .homeAddress(homeAddress)
                .workAddress(workAddress)
                .favorites(favorites)
                .name(name)
                .build();
    }
}
