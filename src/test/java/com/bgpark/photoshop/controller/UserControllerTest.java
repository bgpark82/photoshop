package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.domain.Address;
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

import java.util.Set;

import static com.bgpark.photoshop.step.UserStep.*;
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

    AddressDto.SaveReq 사용자_집주소;
    AddressDto.SaveReq 사용자_회사주소;
    Set<String> 사용자_관심분야;
    String 이름;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        이름 = "박병길";
        사용자_집주소 = 사용자_집주소("서울", "가산동", "롯데백화점", 2468);
        사용자_회사주소 = 사용자_회사주소("서울", "가산동", "롯데백화점", 2468);
        사용자_관심분야 = 사용자_관심분야("portrait", "landscape");
    }

    @Test
    void 회원가입() throws Exception {
        UserDto.Req userReq = 사용자(이름, 사용자_집주소, 사용자_회사주소, 사용자_관심분야);
        String request = mapper.writeValueAsString(userReq);

        mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("박병길"));
    }


}