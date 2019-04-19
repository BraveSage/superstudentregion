package com.superstudentregion.exception;

import com.superstudentregion.util.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    public ExceptionHandlerAdvice() {
    }

    @ResponseBody
    @ExceptionHandler({UserException.class})
    public Result handleException(UserException e) {
        return new Result(e.getStatusCode(), e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({ArticleException.class})
    public Result handleException(ArticleException e) {
        return new Result(e.getStatusCode(), e.getMessage());
    }
}
