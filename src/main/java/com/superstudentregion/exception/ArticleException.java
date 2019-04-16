package com.superstudentregion.exception;

public class ArticleException extends BaseException {
    private int statusCode = 500;

    public ArticleException(String message) {
        super(message);
    }

    public ArticleException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
