package com.hbx.study.se.grammer.schedule;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 一个task queue，一个内部线程去扫描queue，执行task
 */
public class TimerL {



    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("任务执行了！");
            }
        }, 0, 1000); // 立即执行，每隔1秒重复执行
    }

}



