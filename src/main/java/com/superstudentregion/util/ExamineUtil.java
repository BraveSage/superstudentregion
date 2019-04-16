package com.superstudentregion.util;

import com.superstudentregion.exception.UserException;

public class ExamineUtil {
    public static void examineUserName(String name) {
        if (name != null && !name.isEmpty()) {
            if (!name.matches("^\\w{6,18}$")) {
                throw new UserException(500, "用户名格式不正确");
            }
        } else {
            throw new UserException(500, "用户名称不可为空");
        }
    }

    public static boolean examineUserEmail(String email) {
        if (email != null && !email.isEmpty()) {
            if (!email.matches("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$")) {
                throw new UserException(500, "用户邮箱格式不正确");
            } else {
                return true;
            }
        } else {
            throw new UserException(500, "用户邮箱不能为空");
        }
    }

    public static boolean examineUserPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            if (!phone.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,3,5-9]))\\d{8}$")) {
                throw new UserException(500, "手机号格式不正确");
            } else {
                return true;
            }
        } else {
            throw new UserException(500, "手机号不能为空");
        }
    }

    public static boolean examineStringIsNull(String str) {
        boolean flag = false;
        if (str == null || str.trim().isEmpty()) {
            flag = true;
        }

        return flag;
    }

    public static void examinExistEmail(Integer flag) {
        switch(flag) {
            case 0:
                throw new UserException(500, "邮箱已经存在，请更换");
            case 1:
                throw new UserException(500, "用户邮箱处于未激活状态，请打开邮箱激活后登录");
            case 2:
                throw new UserException(500, "邮箱已经存在，请更换");
            default:
        }
    }

    public static void examinExistUserName(Integer flag) {
        switch(flag) {
            case 0:
                throw new UserException(500, "用户名已经存在，请更换");
            case 1:
                throw new UserException(500, "该用户名处于未激活状态，请打开邮箱激活后登录");
            case 2:
                throw new UserException(500, "用户名已经存在，请更换");
            default:
        }
    }
}
