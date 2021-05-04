package com.bgpark.photoshop.domain.user.dto;

import com.bgpark.photoshop.domain.user.domain.Address;
import com.bgpark.photoshop.domain.user.domain.User;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Address homeAddress;
    private Address workAddress;
    private Set<String> favorites;
    private List<Address> homeAddressHistory;

    public static UserResponse create(Long id, String name, String email, String password, Address homeAddress, Address workAddress, Set<String> favorites, List<Address> homeAddressHistory) {
        UserResponse response = new UserResponse();
        response.id = id;
        response.name = name;
        response.email = email;
        response.password = password;
        response.homeAddress = homeAddress;
        response.workAddress = workAddress;
        response.favorites = favorites;
        response.homeAddressHistory = homeAddressHistory;
        return response;
    }

    public static UserResponse of(User user) {
        UserResponse response = new UserResponse();
        response.email = user.getEmail();
        response.password = user.getPassword();
        response.favorites = user.getFavorites();
        response.homeAddress = user.getHomeAddress();
        response.workAddress = user.getWorkAddress();
        response.name = user.getName();
        response.homeAddressHistory = user.getHomeAddressHistory();
        response.id = user.getId();
        return response;
    }
}
