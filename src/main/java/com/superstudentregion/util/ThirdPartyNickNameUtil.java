package com.superstudentregion.util;

public class ThirdPartyNickNameUtil {
    public static String createName(String type) {
        return type + "_" + random() + curr();
    }

    public static String random() {
        String num = "";
        num = num + String.valueOf((int)(Math.random() * 1000.0D));
        return num;
    }

    public static String curr() {
        long timeMillis = System.currentTimeMillis();
        String string = Long.toString(timeMillis);
        String temp = string.substring(0, 8);
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(createName("qq"));
    }
}
