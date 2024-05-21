package com.mftplus.person.exception;

public class NoContentException extends ExceptionPattern{

    public NoContentException(String message) {
        super(message);
        setStatusCode(404);
    }
}
