package com.asset.foundation.utility.exception;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);

    }

    public ForbiddenException(String message) {
        super(message);

    }

}
