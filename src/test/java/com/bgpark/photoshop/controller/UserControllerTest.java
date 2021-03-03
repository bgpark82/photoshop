package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.dto.AddressDto;
import com.bgpark.photoshop.dto.UserDto;
import com.bgpark.photoshop.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void 회원가입() throws Exception {
        String name = "박병길";
        AddressDto.SaveReq homeAddressReq = createHomeAddressRequestMock();
        AddressDto.SaveReq workAddressReq = createWorkAddressRequestMock();
        HashSet<String> favoritesReq = createFavoritesMock();
        UserDto.SaveReq userReq = UserDto.SaveReq
                .builder()
                .homeAddress(homeAddressReq)
                .workAddress(workAddressReq)
                .favorites(favoritesReq)
                .name(name)
                .build();

        String request = mapper.writeValueAsString(userReq);

        given(userService.save(any())).willReturn(userReq.toEntity());

        mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("박병길"));
    }

    private HashSet<String> createFavoritesMock() {
        HashSet<String> favoritesReq = new HashSet<>();
        favoritesReq.add("portrait");
        favoritesReq.add("landscape");
        return favoritesReq;
    }

    private AddressDto.SaveReq createWorkAddressRequestMock() {
        return AddressDto.SaveReq
                .builder()
                .city("서울")
                .street("가산동")
                .detail("롯데백화점")
                .zipcode(2468)
                .build();
    }

    private AddressDto.SaveReq createHomeAddressRequestMock() {
        return AddressDto.SaveReq
                .builder()
                .city("서울")
                .street("서초대로")
                .detail("201")
                .zipcode(1234)
                .build();
    }
}