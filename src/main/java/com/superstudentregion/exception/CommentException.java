package com.superstudentregion.exception;

public class CommentException extends BaseException {
    private int statusCode = 500;

    public CommentException(String message) {
        super(message);
    }

    public CommentException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
