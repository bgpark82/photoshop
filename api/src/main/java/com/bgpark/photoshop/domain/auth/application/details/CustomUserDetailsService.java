package com.bgpark.photoshop.domain.auth.application.details;

import com.bgpark.photoshop.domain.auth.application.UserDetails;
import com.bgpark.photoshop.domain.user.domain.User;
import com.bgpark.photoshop.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("사용자가 존재하지 않습니다"));
        return UserDetails.of(user.getId(), user.getEmail(), user.getPassword());
    }
}
