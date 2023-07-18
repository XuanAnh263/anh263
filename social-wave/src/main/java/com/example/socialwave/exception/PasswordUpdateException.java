package com.example.socialwave.exception;

public class PasswordUpdateException extends RuntimeException{
    public PasswordUpdateException(String message) {
        super(message);
    }
    public PasswordUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
