package com.bgpark.photoshop.domain.user.application;

import com.bgpark.photoshop.domain.user.domain.User;
import com.bgpark.photoshop.domain.user.domain.UserRepository;
import com.bgpark.photoshop.domain.user.dto.UserRequest;
import com.bgpark.photoshop.domain.user.dto.UserResponse;
import com.bgpark.photoshop.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse save(UserRequest request) {
        User user = userRepository.save(request.toEntity());
        return UserResponse.of(user);
    }

    public UserResponse findUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        return UserResponse.of(user);
    }
}
