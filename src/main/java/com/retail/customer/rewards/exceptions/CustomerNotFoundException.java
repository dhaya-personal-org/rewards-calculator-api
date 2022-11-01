package com.retail.customer.rewards.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
    public CustomerNotFoundException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
