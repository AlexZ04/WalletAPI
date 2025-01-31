package ru.cft.template.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
