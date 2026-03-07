package com.edutech.progressive.exception;

public class NoWarehouseFoundForSupplierException extends RuntimeException {
    public NoWarehouseFoundForSupplierException() {
        super();
    }
    public NoWarehouseFoundForSupplierException(String message) {
        super(message);
    }
    public NoWarehouseFoundForSupplierException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoWarehouseFoundForSupplierException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
} 