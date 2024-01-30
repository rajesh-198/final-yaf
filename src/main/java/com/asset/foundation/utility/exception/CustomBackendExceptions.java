package com.asset.foundation.utility.exception;

public class CustomBackendExceptions extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CustomBackendExceptions(String message, Throwable cause) {
        super(message, cause);

    }

    public CustomBackendExceptions(String message) {
        super(message);

    }

}
