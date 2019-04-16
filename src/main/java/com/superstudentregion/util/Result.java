package com.superstudentregion.util;

import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class Result<T> {
    private int errCode = 0;
    private String errInfo;
    private Object errData;

    public Result() {
    }

    public Result(int errCode, String errInfo) {
        this.errCode = errCode;
        this.errInfo = errInfo;
    }

    public Result(int errCode, T errData) {
        this.errCode = errCode;
        this.errData = errData;
    }

    public Result(int errCode, String errInfo, T errData) {
        this.errCode = errCode;
        this.errInfo = errInfo;
        this.errData = errData;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result();
        result.setErrCode(0);
        result.setErrInfo((String)null);
        return result;
    }

    public static <T> Result<T> success(String Info) {
        Result<T> result = success();
        result.setErrInfo(Info);
        return result;
    }

    public static <T> Result<T> success(String Info, T respBody) {
        Result<T> result = new Result();
        result.setErrCode(0);
        result.setErrData(respBody);
        result.setErrInfo(Info);
        return result;
    }

    public static <T> Result<T> success(List<T> respBody) {
        new ArrayList();
        Result<T> result = success();
        result.setErrData(new PageInfo(respBody));
        return result;
    }

    public static <T> Result<T> failure(int code, String info) {
        Result<T> result = new Result();
        result.setErrCode(code);
        result.setErrInfo(info);
        return result;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrInfo() {
        return this.errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

    public Object getErrData() {
        return this.errData;
    }

    public void setErrData(Object errData) {
        this.errData = errData;
    }
}
