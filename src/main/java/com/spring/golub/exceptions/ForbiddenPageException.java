package com.spring.golub.exceptions;

public class ForbiddenPageException extends RuntimeException {
    public ForbiddenPageException(String message) {
        super(message);
    }
}