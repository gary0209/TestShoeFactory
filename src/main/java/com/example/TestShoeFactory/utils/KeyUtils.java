package com.example.TestShoeFactory.utils;

import java.time.LocalDate;
import java.util.Random;

public class KeyUtils {

    public static String getKeyByDate(String date, String name) {
        Random rm = new Random();
        LocalDate localDate = DateUtils.parseDate(date, null);
        int len = localDate.getDayOfMonth() % 2 != 0 ? 3 : 4;
        double pross = (1 + rm.nextDouble()) * Math.pow(10, len);
        String k = String.valueOf(pross);
        return k.substring(1, len + 1).concat(name.toUpperCase());
    }

    /**
     * recursive string
     * @param s
     * @return
     */
    public static String recursiveString(String s) {
        int len = s.length();
        char[] charArray = s.toCharArray();
        String result = "";
        if (len > 1) {
            for (int i = len - 1; i >=0 ; i--) {
                result += charArray[i];
            }
        }

        return result;
    }


    public static void main(String args[]) {
//        String s = getKeyByDate("2020-02-28", "shoe");
        String s = recursiveString( "Ser DevOps");
        System.out.println(s);
    }
}
