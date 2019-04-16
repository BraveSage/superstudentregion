package com.superstudentregion.exception;

public class UserException extends BaseException {
    private int statusCode = 500;

    public UserException(String message) {
        super(message);
    }

    public UserException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
