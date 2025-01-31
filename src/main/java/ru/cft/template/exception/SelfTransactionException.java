package ru.cft.template.exception;

public class SelfTransactionException extends RuntimeException {
    public SelfTransactionException(String message) {
        super(message);
    }
}
