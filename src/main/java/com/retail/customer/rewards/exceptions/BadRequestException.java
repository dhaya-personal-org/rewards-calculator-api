package com.retail.customer.rewards.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {

    }
    public BadRequestException(String msg) {
        super(msg);
    }
    public BadRequestException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
    public BadRequestException(Throwable throwable) {
        super(throwable);
    }
}
