package com.boat.pensionwx.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    /***
     * 将日期Date转化成字符串
     * @param date "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String date2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /***
     * 将日期+时间Date转化成字符串
     * @param date "yyyy-MM-dd"
     * @return
     */
    public static String dateTime2String(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return format.format(date);
    }

    /***
     * 将日期+时间Date转化成字符串，并且只保留时间部分
     * @param date "HH:mm:ss"
     * @return
     */
    public static String time2String(Date date){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }
    /***
     * 将字符串转化成日期
     * @param s "yyyy-MM-dd"
     * @return
     */
    public static Date string2Date(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 将字符串转化成日期+时间
     * @param s "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static Date string2DateTime(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(s);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /***
     * 求出两时间的差值
     * @param d1 靠后的时间
     * @param d2 先前的时间
     * @return
     */
    public static String difference(Date d1, Date d2) {
        long d = d1.getTime() - d2.getTime();
        long all = d / 1000;
        long hours = all / 3600;
        long minutes = (all % 3600) / 60;
        long seconds = (all % 3600) % 60;
        if (hours > 0)
            return hours + "时" + minutes + "分" + seconds + "秒";
        else if (minutes > 0) {
            return minutes + "分" + seconds + "秒";
        } else
            return seconds + "秒";
    }
}
