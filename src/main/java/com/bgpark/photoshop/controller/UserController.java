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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> save(@RequestBody UserDto.SaveReq request) {
        User user = request.toEntity();
        return ResponseEntity.ok(userService.save(user));
    }
}
