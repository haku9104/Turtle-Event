package com.codecool.turtleevent.service;

public class ToBringNotFoundException extends Exception{

    public ToBringNotFoundException() {
    }

    public ToBringNotFoundException(String message) {
        super(message);
    }

    public ToBringNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToBringNotFoundException(Throwable cause) {
        super(cause);
    }

    public ToBringNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
