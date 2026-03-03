package com.edutech.progressive.exception;

public class SupplierAlreadyExistsException extends RuntimeException {
    public SupplierAlreadyExistsException() {
        super();
    }
    public SupplierAlreadyExistsException(String message) {
        super(message);
    }
    public SupplierAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
} 