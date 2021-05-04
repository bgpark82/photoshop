package com.bgpark.photoshop.domain.user.dto;

import com.bgpark.photoshop.domain.user.domain.Address;
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
        UserResponse request = new UserResponse();
        request.id = id;
        request.name = name;
        request.email = email;
        request.password = password;
        request.homeAddress = homeAddress;
        request.workAddress = workAddress;
        request.favorites = favorites;
        request.homeAddressHistory = homeAddressHistory;
        return request;
    }
}
