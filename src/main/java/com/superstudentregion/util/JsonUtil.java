package com.superstudentregion.util;

import com.alibaba.fastjson.JSON;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class JsonUtil {
    public static String toJson(Object obj) {
        try {
            return JSON.toJSONString(obj);
        } catch (Exception var2) {
            var2.printStackTrace();
            throw new RuntimeException("转换json字符失败!");
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception var3) {
            throw new RuntimeException("将json字符转换为对象时失败!");
        }
    }

    public static <T> List<T> toList(String text, Class<T> clazz) {
        try {
            return JSON.parseArray(text, clazz);
        } catch (Exception var3) {
            throw new RuntimeException("将json字符转换为List时失败!");
        }
    }

    public static Object ByteToObject(byte[] bytes) {
        Object obj = null;

        try {
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);
            obj = oi.readObject();
            bi.close();
            oi.close();
        } catch (Exception var4) {
            System.out.println("translation" + var4.getMessage());
            var4.printStackTrace();
        }

        return obj;
    }

    public static byte[] ObjectToByte(Object obj) {
        byte[] bytes = null;

        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception var4) {
            System.out.println("translation" + var4.getMessage());
            var4.printStackTrace();
        }

        return bytes;
    }
}
