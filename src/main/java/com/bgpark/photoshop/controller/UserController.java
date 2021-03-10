package com.bgpark.photoshop.controller;

import com.bgpark.photoshop.domain.User;
import com.bgpark.photoshop.dto.UserDto;
import com.bgpark.photoshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserDto.Res> save(@RequestBody UserDto.Req request) {
        UserDto.Res response = userService.save(request);
        return ResponseEntity
                .created(URI.create(String.format("/user/%d",response.getId())))
                .body(response);
    }
}
