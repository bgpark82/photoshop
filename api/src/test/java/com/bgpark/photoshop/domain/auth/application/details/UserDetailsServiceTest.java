package com.bgpark.photoshop.domain.auth.application.details;

import com.bgpark.photoshop.domain.user.domain.UserRepository;
import com.bgpark.photoshop.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

@DisplayName("UserDetail 관련 서비스 테스트")
class UserDetailsServiceTest {

    UserDetailsService userDetailsService;
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userDetailsService = new CustomUserDetailsService(userRepository);
    }

    @DisplayName("회원가입이 안된 경우 UserNotFoundException 발생")
    @Test
    void signUpRequired() {
        // given
        String email = "bgpark82@gmail.com";

        // when
        assertThatThrownBy(() -> Optional.ofNullable(userDetailsService.loadByUsername(email)))
                .isInstanceOf(UserNotFoundException.class);
    }
}