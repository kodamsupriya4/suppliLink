package com.edutech.progressive.exception;

public class SupplierDoesNotExistException extends RuntimeException {
    public SupplierDoesNotExistException() {
        super();
    }
    public SupplierDoesNotExistException(String message) {
        super(message);
    }
    public SupplierDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public SupplierDoesNotExistException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
} 