package com.hbx.study.se.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

/**
 * 死锁
 * 核心：占用资源的同时申请占用其他资源，形成循环等待
 */
@Slf4j
public class DeadLock {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            synchronized (lock1) {
                log.info("占据资源1...");
                try {
                    cyclicBarrier.await();
                    log.info("尝试占据资源2...");
                    synchronized (lock2) {
                        log.info("占据资源2...");
                    }
                } catch (Exception e){

                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                log.info("占据资源2...");
                try {
                    cyclicBarrier.await();
                    log.info("尝试占据资源1...");
                    synchronized (lock1) {
                        log.info("占据资源1...");
                    }
                } catch (Exception e){

                }
            }
        }).start();
    }
}
