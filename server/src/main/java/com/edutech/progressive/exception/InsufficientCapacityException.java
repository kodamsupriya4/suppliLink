package com.edutech.progressive.exception;

public class InsufficientCapacityException extends RuntimeException {
    public InsufficientCapacityException() {
        super();
    }
    public InsufficientCapacityException(String message) {
        super(message);
    }
    public InsufficientCapacityException(String message, Throwable cause) {
        super(message, cause);
    }
    public InsufficientCapacityException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
} 