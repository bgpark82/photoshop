package com.bgpark.photoshop.domain.user.ui;

import com.bgpark.photoshop.domain.auth.application.SecurityContextHolder;
import com.bgpark.photoshop.domain.auth.domain.SecurityContext;
import com.bgpark.photoshop.domain.auth.domain.UserDetails;
import com.bgpark.photoshop.domain.user.application.UserService;
import com.bgpark.photoshop.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;

import static com.bgpark.photoshop.domain.auth.application.SecurityContextHolder.SECURITY_CONTEXT_KEY;

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

    @GetMapping("/users/me")
    public ResponseEntity findMe(HttpServletRequest request) {
        UserDetails userDetails = getUserDetails();
        UserDto.Res user = userService.findUser(userDetails.getPrincipal());
        return ResponseEntity.ok().body(user);
    }

    private UserDetails getUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
