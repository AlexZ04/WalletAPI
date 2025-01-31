package ru.cft.template.exception;

public class SessionAlreadyInactiveException extends RuntimeException {
    public SessionAlreadyInactiveException(String message) {
        super(message);
    }
}
