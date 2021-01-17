package com.spring.golub.exceptions;

public class IllegalEmailException extends RuntimeException {
    public IllegalEmailException(Throwable cause) {
        super(cause);
    }
}
