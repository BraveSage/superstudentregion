package com.superstudentregion.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class TokenUtils {
    public static String generateToken(Integer userId, String userEmail) {
        if (userId != null && userEmail != null) {
            String tokenInfo = userId + userEmail + System.currentTimeMillis();
            byte[] secretBytes = null;

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                secretBytes = md.digest(tokenInfo.getBytes());
            } catch (NoSuchAlgorithmException var5) {
                var5.printStackTrace();
            }

            return secretBytes != null ? (new BigInteger(1, secretBytes)).toString(16) : "";
        } else {
            throw new IllegalArgumentException("Argument userId and userEmail is null!");
        }
    }

    public static boolean isTokenValid(String token, String verifyToken) {
        return Objects.equals(token, verifyToken);
    }

    public static void main(String[] args) {
        Integer[] testUserId = new Integer[]{100000, 100001, 100002, 100006, 0};
        String[] testEmail = new String[]{"1532033525@qq.com", "starkzhidian@gamil.com", "2877225246@qq.com", "13979421275@163.com", ""};

        for (int i = 0; i < testUserId.length; ++i) {
            String token = generateToken(testUserId[i], testEmail[i]);
            System.out.println(token);
            System.out.println(isTokenValid(token, token));
        }

    }
}
