package com.codecool.turtleevent.service;

public class ToDoNotFoundException extends Exception{

    public ToDoNotFoundException() {
    }

    public ToDoNotFoundException(String message) {
        super(message);
    }

    public ToDoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ToDoNotFoundException(Throwable cause) {
        super(cause);
    }

    public ToDoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
