package com.example.TestShoeFactory.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    /**
     * yyyy-MM-dd格式字符串转日期
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static LocalDate parseDate(String dateStr, String pattern) {
        if ((dateStr == null) || (dateStr.equals(""))) {
            return null;
        }

        if (pattern == null) {
            pattern = "yyyy-MM-dd";
        }
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        return date;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(LocalDate date, String pattern) {
        if (date == null)
            return "";
        if (pattern == null)
            pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return date.format(formatter);
    }


    /**
     * get random date
     *
     * @param date
     * @return
     */
    public static String randBetweenMonth(String date) {
        LocalDate localDate = parseDate(date, null);
        int month = localDate.getMonthValue();
        int days = localDate.lengthOfMonth();

        int day =  1 + (int)Math.round(Math.random() * (days - 1));
        return formatDate(LocalDate.of(localDate.getYear(), month, day), null);
    }
}
