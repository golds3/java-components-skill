package com.hbx.study.se.juc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * jdk 21 新增虚拟线程-协程
 * 多个虚拟线程对应一个操作系统线程
 * 虚拟线程都是守护线程，优先级是normal，不允许更改
 * 虚拟线程不建议使用线程池 -- 随用随消
 */
@Slf4j
public class VirtualThread {

    public static void main(String[] args) throws InterruptedException {
        log.info("开启虚拟线程...");
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread.startVirtualThread(() -> {
            log.info("hello world,id:{}", Thread.currentThread().threadId());
            countDownLatch.countDown();
        });

        Thread.ofPlatform().name("平台线程-传统线程，和内核一一对应").start(() -> {
            log.info("hello world-2,id:{}", Thread.currentThread().threadId());
            countDownLatch.countDown();
        });

        Thread.ofVirtual().name("虚拟线程").start(() -> {
            log.info("hello world-3,id:{}", Thread.currentThread().threadId());
            countDownLatch.countDown();
        });

        countDownLatch.await();

        //性能测试
        int taskCount = 100000;

        // 测试平台线程
        StopWatch platformWatch = new StopWatch();
        platformWatch.start("平台线程");
        testPlatformThread(taskCount);
        platformWatch.stop();
        System.out.println("普通线程耗时: " + platformWatch.prettyPrint());

        // 测试虚拟线程
        StopWatch virtualWatch = new StopWatch();
        virtualWatch.start("虚拟线程");
        testVirtualThread(taskCount);
        virtualWatch.stop();
        System.out.println("虚拟线程耗时: " + virtualWatch.prettyPrint());
    }

    public static void testPlatformThread(int taskCount) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < taskCount; i++){
            executorService.submit(()->{
                task();
            countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdownNow();
    }

    public static void testVirtualThread(int taskCount) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);
        for (int i = 0; i < taskCount; i++){
            Thread.ofVirtual().start(()->{
                task();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
    }

    /**
     * 性能测试
     */
    public static void task() {
        try {
            IntStream.range(0, 100).forEach(i -> {
                Math.sqrt(i);
            });
            Thread.sleep(10);
        } catch (InterruptedException e) {

        }
    }
}
