package ru.cft.template.exception;

public class UsedCredentialsException extends RuntimeException {
    public UsedCredentialsException(String message) {
        super(message);
    }
}
