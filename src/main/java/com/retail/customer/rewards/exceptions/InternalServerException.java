package com.retail.customer.rewards.exceptions;

public class InternalServerException extends RuntimeException{
    public InternalServerException(String msg) {
        super(msg);
    }
    public InternalServerException(String msg, Throwable ex) {
        super(msg, ex);
    }
    public InternalServerException(Throwable th) {
        super(th);
    }
}
