package com.superstudentregion.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CurrentTimeUtil {
    public static void aaa() {
        long totalMilliSeconds = System.currentTimeMillis();
        DateFormat dateFormatterChina = DateFormat.getDateTimeInstance(2, 2);
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Beijing");
        dateFormatterChina.setTimeZone(timeZoneChina);
        long totalSeconds = totalMilliSeconds / 1000L;
        long currentSecond = totalSeconds % 60L;
        long totalMinutes = totalSeconds / 60L;
        long currentMinute = totalMinutes % 60L;
        long totalHour = totalMinutes / 60L;
        long currentHour = totalHour % 24L;
        System.out.println("总毫秒为： " + totalMilliSeconds);
        System.out.println(currentHour + ":" + currentMinute + ":" + currentSecond + " GMT");
        Date nowTime = new Date(System.currentTimeMillis());
        System.out.println(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        System.out.println(retStrFormatNowDate);
    }

    public static void main(String[] args) {
        long totalMilliSeconds = 9999L;
        long totalSeconds = totalMilliSeconds / 1000L;
        long currentSecond = totalSeconds % 60L;
        long totalMinutes = totalSeconds / 60L;
        long currentMinute = totalMinutes % 60L;
        long totalHour = totalMinutes / 60L;
        long currentHour = totalHour % 24L;
        long totalDay = totalHour / 24L;
        System.out.println(currentSecond);
        System.out.println("总共秒数为" + totalSeconds);
        System.out.println(currentMinute);
        System.out.println("总分钟数为" + totalMinutes);
        System.out.println(currentHour);
        System.out.println("当前天数为" + totalDay);
        System.out.println(3);
        boolean duration = duration(totalMilliSeconds);
        System.out.println(duration);
    }

    public static boolean duration(long time) {
        long seconds = 1000L;
        long minute = 60L;
        long totalTime = seconds * minute * 10L;
        return time >= totalTime;
    }
}
