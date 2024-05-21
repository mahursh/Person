package com.mftplus.person.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ExceptionPattern extends Exception {

    private int statusCode;
    private String statusText;

    public ExceptionPattern(String message) {
        super(message);
        statusText = message;
    }
}
