package com.bgpark.photoshop.exception;

public class EmptyMultipartException extends ApplicationException{

    private static final String ERROR_MESSAGE = "비어있는 multipartfile 입니다";

    public EmptyMultipartException() {
        super(ERROR_MESSAGE);
    }
}
