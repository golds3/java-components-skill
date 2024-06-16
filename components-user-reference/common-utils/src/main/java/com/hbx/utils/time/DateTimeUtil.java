package com.hbx.utils.time;

import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;

/**
 * 工具类使用 LocalDateTime
 * 格式类使用 DateTimeFormatter
 */
@UtilityClass
public class DateTimeUtil {

    /**
     * 获取当前 日期
     */
    public static LocalDate getDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前 时间
     *
     * @return
     */
    public static LocalTime getTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前 日期和时间
     */
    public static LocalDateTime getDateAndTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取日期差
     *
     * @param date1
     * @param date2
     */
    public static Duration getTimeDiff(LocalDate date1, LocalDate date2) {
        return Duration.between(date1, date2);
    }

    /**
     * 格式化日期
     * @param temporal
     * @param pattern
     * @return
     */
    public static String formatTime(Temporal temporal, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        String format = dateTimeFormatter.format(temporal);
        return format;
    }

    /**
     * 解析字符串日期
     * @param pattern
     * @param time
     * @return
     */
    public static LocalDateTime parseTime(String pattern,String time){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime parse = LocalDateTime.parse(time, dateTimeFormatter);
        return parse;
    }

}
