package com.bgpark.photoshop.service;

import com.bgpark.photoshop.domain.User;
import com.bgpark.photoshop.dto.UserDto;
import com.bgpark.photoshop.repository.UserRepository;
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
