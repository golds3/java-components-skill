package com.hbx.study.se.juc.sense;

import java.util.concurrent.*;

/**
 * 三个线程按顺序打印
 * 1.join 2.CountDownLatch 3.Semaphore 4.CyclicBarrier 5.executor 6.CompletableFuture
 */
public class ThreeThreadOrderly {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(()->{
            System.out.println("1-"+Thread.currentThread().getName());
        }).thenRunAsync(()->{
            System.out.println("2-"+Thread.currentThread().getName());
        }).thenRunAsync(()->{
            System.out.println("3-"+Thread.currentThread().getName());
        }).get();

    }
}
