package com.hbx.study.se.grammer.date;

import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * 这个是线程不安全的，是因为SimpleDateFormat内部的calendar对象（存储时间）是共享的
 * 可以使用线程安全的DateTimeFormatter进行替换
 */
public class SimpleDateFormatL {
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 基本用法 把string按照给定格式转换为日期
     */
    public static void baseUse() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        System.out.println(format);
        Date parse = sdf.parse(format);
        System.out.println( parse);
        //设置时区
        sdf.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        String americaDate = sdf.format(date);
        System.out.println(americaDate);
    }
    public static void main(String[] args) throws ParseException, InterruptedException {
        baseUse();

        //复现线程不安全
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            Calendar calendar = Calendar.getInstance();
            int finalI = i;
            Thread.startVirtualThread(()->{
                calendar.add(Calendar.DATE, finalI);
                // format 方法里面关键的地方calendar.setTime(date); 用了一个成员变量calendar记录时间
                // 多个线程同时对日期进行+1，就会导致时间被覆盖，那么后面放入set就会被去重了
                String format = sdf.format(calendar.getTime());
                set.add(format);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println(set.size());
        Assert.isTrue(set.size() != 100,"concurrent");

        //使用DateTimeFormatter 进行安全处理
        Set<String> set2 = Collections.synchronizedSet(new HashSet<>());
        CountDownLatch countDownLatch2 = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            Thread.startVirtualThread(()->{
                LocalDate plus = LocalDate.now().plus(finalI, ChronoUnit.DAYS);
                // format 方法里面关键的地方calendar.setTime(date); 用了一个成员变量calendar记录时间
                // 多个线程同时对日期进行+1，就会导致时间被覆盖，那么后面放入set就会被去重了
                String format = plus.format(dtf);
                set2.add(format);
                countDownLatch2.countDown();
            });
        }
        countDownLatch2.await();
        System.out.println(set2.size());
        Assert.isTrue(set2.size() == 100,"concurrent");



    }
}
