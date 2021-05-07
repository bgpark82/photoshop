package com.bgpark.photoshop.domain.user.ui;

import com.bgpark.photoshop.domain.auth.domain.Principal;
import com.bgpark.photoshop.domain.auth.domain.UserDetails;
import com.bgpark.photoshop.domain.user.application.UserService;
import com.bgpark.photoshop.domain.user.dto.UserRequest;
import com.bgpark.photoshop.domain.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.save(request);
        return ResponseEntity
                .created(URI.create(String.format("/user/%d",response.getId())))
                .body(response);
    }

    @GetMapping("/users/me")
    public ResponseEntity findMe(@Principal UserDetails userDetails) {
        UserResponse user = userService.findUser(userDetails.getPrincipal());
        return ResponseEntity.ok().body(user);
    }
}
