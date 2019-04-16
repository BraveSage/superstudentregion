package com.superstudentregion.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static int differentDays(String date1, String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat();

        try {
            Date parse1 = dateFormat.parse(date1);
            Date parse2 = dateFormat.parse(date2);
            return differentDays(parse1, parse2);
        } catch (ParseException var5) {
            var5.printStackTrace();
            return 0;
        }
    }

    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(6);
        int day2 = cal2.get(6);
        int year1 = cal1.get(1);
        int year2 = cal2.get(1);
        if (year1 == year2) {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        } else {
            int timeDistance = 0;

            for(int i = year1; i < year2; ++i) {
                if ((i % 4 != 0 || i % 100 == 0) && i % 400 != 0) {
                    timeDistance += 365;
                } else {
                    timeDistance += 366;
                }
            }

            return timeDistance + (day2 - day1);
        }
    }
}
