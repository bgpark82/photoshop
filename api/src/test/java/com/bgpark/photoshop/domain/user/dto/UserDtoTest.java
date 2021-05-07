package com.bgpark.photoshop.domain.user.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자 dto 관련 테스트")
class UserDtoTest {

    Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }

    @DisplayName("유효한 이메일인지 체크한다")
    @Test
    void checkEmail() throws ClassNotFoundException {
        // given
        UserRequest request = createRequest();

        // when
        Set<ConstraintViolation<UserRequest>> validate = validator.validate(request);

        // then
        for (ConstraintViolation<UserRequest> violation : validate) {
            assertThat(violation.getMessage()).isEqualTo("이메일 주소가 유효하지 않습니다.");
        }
    }

    private UserRequest createRequest() {
        UserRequest request = new UserRequest();
        ReflectionTestUtils.setField(request, "email", "email");
        ReflectionTestUtils.setField(request, "password", "password");
        return request;
    }
}