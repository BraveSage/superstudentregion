package com.superstudentregion.util;

import com.superstudentregion.bean.UserInfo;

import javax.servlet.ServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateLinkUtil {
    private static final String USER_NAME_KEY = "yuercd";
    private static final String CHECK_CODE = "dsjs";
    private static final String SEND_TIME_KEY = "jgssd";
    private static final String USER_EMAIL_KEY = "cfyad";

    public GenerateLinkUtil() {
    }

    public static String getUserEmailKey() {
        return "cfyad";
    }

    public static String getUserNameKey() {
        return "yuercd";
    }

    public static String getCheckCode() {
        return "dsjs";
    }

    public static String getSendTimeKey() {
        return "jgssd";
    }

    public static String generateActivateLink(UserInfo user) {
        return "http://193.112.79.70:8080/sturegion/user/active?yuercd=" + user.getUserName() + "&" + "dsjs" + "=" + generateCheckcode(user) + "&" + "jgssd" + "=" + System.currentTimeMillis();
    }

    public static String generateActivateLink(String email) {
        return "http://193.112.79.70:8080/sturegion/user/alterPwd?cfyad=" + email + "&" + "jgssd" + "=" + System.currentTimeMillis();
    }

    public static String generateCheckcode(UserInfo user) {
        String userName = user.getUserName();
        String randomCode = user.getCodeUrl();
        return md5(userName + ":" + randomCode);
    }

    public static boolean verifyCheckcode(UserInfo user, ServletRequest request) {
        String checkCode = request.getParameter("dsjs");
        System.out.println(generateCheckcode(user).equals(checkCode));
        return true;
    }

    private static String md5(String string) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
            System.out.println("md5这里出错了");
            return null;
        }
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuffer strBuf = new StringBuffer();

        for(int i = 0; i < byteArray.length; ++i) {
            if (byteArray[i] >= 0 && byteArray[i] < 16) {
                strBuf.append("0");
            }

            strBuf.append(Integer.toHexString(byteArray[i] & 255));
        }

        return strBuf.toString();
    }

    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();

        for(int i = 0; i < a.length; ++i) {
            a[i] = (char)(a[i] ^ 116);
        }

        String s = new String(a);
        return s;
    }

    public static void main(String[] args) {
        String s = md5("1120891211@qq.com");
        System.out.println(s);
        String s1 = convertMD5(s);
        System.out.println(s1);
        String s2 = convertMD5(s1);
        System.out.println(s2);
    }
}
