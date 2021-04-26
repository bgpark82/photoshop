package com.bgpark.photoshop.domain.user.application;

import com.bgpark.photoshop.domain.user.domain.User;
import com.bgpark.photoshop.domain.user.domain.UserRepository;
import com.bgpark.photoshop.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserDto.Res save(UserDto.Req request) {
        User user = userRepository.save(request.toEntity());
        return UserDto.Res.of(user);
    }
}
