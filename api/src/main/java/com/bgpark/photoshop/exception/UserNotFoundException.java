package com.bgpark.photoshop.exception;

public class UserNotFoundException extends ApplicationException{

    private static final String ERROR_MESSAGE = "회원을 찾을 수 없습니다";

    public UserNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
