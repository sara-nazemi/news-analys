package com.example.newscall.exception;

public class NewsException extends RuntimeException {
    public NewsException() {
    }

    public NewsException(String message) {
        super(message);
    }

    public NewsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NewsException(Throwable cause) {
        super(cause);
    }

    public NewsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
