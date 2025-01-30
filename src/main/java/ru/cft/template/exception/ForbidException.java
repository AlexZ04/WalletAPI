package ru.cft.template.exception;

public class ForbidException extends  RuntimeException {
    public ForbidException(String message) {
        super(message);
    }
}
