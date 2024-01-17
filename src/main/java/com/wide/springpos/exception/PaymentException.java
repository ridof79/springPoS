package com.wide.springpos.exception;

public class PaymentException extends Exception {

    public PaymentException() {
    }
    public PaymentException(String message) {
        super(message);
    }
}
