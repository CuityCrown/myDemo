package com.ryml.util;

import javax.validation.constraints.NotNull;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @description 日期工具类  基于jdk 1.8
 * @author 毛双领
 * @create 2019-09-07 13:59
 */
public final class DateUtil {
    private static final String[] strWeekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    private static final int[] intWeekDays = {7,1,2,3,4,5,6};
    public static final String SHORT_PATTERN = "yyyy-MM-dd";
    public static final String MIDDLE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String LONG_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String LONG_PATTERN_S = "yyyy-MM-dd HH:mm:ss.S";
    public static final String LONG_PATTERN_SS = "yyyy-MM-dd HH:mm:ss.SS";
    public static final String LONG_PATTERN_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    public static void main(String[] args) {
        /*System.out.println(getWeekOfDate(new Date()));
        System.out.println(tryParse("2019-09-07 16:07:34"));
        System.out.println(tryParse("2019-09-07 16:07:34"));
        String str = "2019-09-07 16:25:25.123";
        System.out.println(getCurrentDate());
        System.out.println(getCurrentDateStr());
        System.out.println(tryParse(str,LONG_PATTERN_SSS));
        System.out.println(format(new Date(),LONG_PATTERN_SSS));

        System.out.println(jointDateAndTime(getCurrentDate(),tryParse("1970-01-01 12:12:12")));*/
        Date date1 = tryParse("2017-02-00 08:00:00", "HH:mm:ss");
        System.out.println(date1);
    }

    /**
     * 获取当前日期
     *
     * @return  Date
     */
    public static Date getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime2Date(localDateTime);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式
     * @return String
     */
    public static String getCurrentDateStr(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return format(localDateTime2Date(localDateTime), pattern);
    }

    /**
     * 获取当前日期,默认格式  yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDateStr() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return format(localDateTime2Date(localDateTime), LONG_PATTERN);
    }

    /**
     * 日期字符串转换为Date类型，如能转换，则返回null
     * @param dateStr
     * @return
     * @author shuangling.mao
     * @date 2018年3月8日 15:25:05
     */
    public static Date tryParse(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        String pattern = null;
        switch (dateStr.length()) {
            case 10: pattern = SHORT_PATTERN; break;
            case 16: pattern = MIDDLE_PATTERN; break;
            case 19: pattern = LONG_PATTERN; break;
            case 21: pattern = LONG_PATTERN_S; break;
            case 22: pattern = LONG_PATTERN_SS; break;
            case 23: pattern = LONG_PATTERN_SSS; break;
            default: throw new RuntimeException("日期" + dateStr + "格式不正确！");
        }
        return tryParse(dateStr, pattern);
    }


    /**
     * 日期字符串转换为Date类型
     * @param dateStr
     * @param pattern
     * @return
     */
    @NotNull
    public static Date tryParse(String dateStr, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     * 格式化日期为字符串
     *
     * @param date date
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String format(Date date,String pattern){
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * 格式化日期为字符串    如不指定，默认转换格式： yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     */
    public static String format(Date date){
        return format(date,LONG_PATTERN);
    }


    /**
     * description: 获取日期是周几
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {w = 0;}
        return intWeekDays[w];
    }

    /**
     * description: 获取日期是周几
     * @param date
     * @return
     */
    public static String getWeekStrOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {w = 0;}
        return strWeekDays[w];
    }


    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
     * @return Date object
     */
    public static Date localDate2Date(LocalDate localDate) {

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * LocalDateTime类型转为Date
     *
     * @param localDateTime LocalDateTime object
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }



    /**
     * 为Date增加分钟,plusMinutes  为负数时  减少分钟
     *
     * @param date        日期
     * @param plusMinutes 要增加的分钟数
     * @return 新的日期
     */
    public static Date addMinutes(Date date, Long plusMinutes) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime newDateTime = dateTime.plusMinutes(plusMinutes);
        return Date.from(newDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增加时间
     *
     * @param date date
     * @param hour 要增加的小时数
     * @return new date
     */
    public static Date addHour(Date date, Long hour) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime = dateTime.plusHours(hour);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * date的年月日 拼接  time的时分秒
     * @param date
     * @param time
     * @return String
     */
    public static String jointDateAndTime(Date date, Date time) {
        if (date == null || time == null) {
            return null;
        }
        return format(date,SHORT_PATTERN) + " " + format(time, "HH:mm:ss");
    }

    /**
     * date的年月日 拼接  time的时分秒
     * @param date
     * @param time
     * @return Date
     */
    public static Date jointDateAndTime2(Date date, Date time) {
        if (date == null || time == null) {
            return null;
        }
        return tryParse(jointDateAndTime(date,time));
    }
}
