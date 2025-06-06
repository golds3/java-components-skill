package com.hbx.study.se.juc;

import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 原理
 * Thread 里面有一个 ThreadLocalMap 类型的属性
 * ThreadLocalMap 里面是一个 Entry 数组
 * Entry 里面是一个 ThreadLocal的弱引用的子类，key就是ThreadLocal对象，value就是对应的值
 *
 * 不同线程的ThreadLocalMap是不同，所以保证了ThreadLocal的线程隔离
 */
@Slf4j
public class ThreadLocalL {

    public static void main(String[] args) throws InterruptedException {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(1);
        CountDownLatch countDownLatch = new CountDownLatch(3);
        log.info("get :{}",threadLocal.get());
        Thread.startVirtualThread(()->{
            log.info("get :{}",threadLocal.get());
            countDownLatch.countDown();
        });

        InheritableThreadLocal<Integer> integerInheritableThreadLocal = new InheritableThreadLocal<>();
        integerInheritableThreadLocal.set(1);
        log.info("get inhert:{}",integerInheritableThreadLocal.get());
        Thread.startVirtualThread(()->{
            log.info("get inhert:{}",integerInheritableThreadLocal.get());
            countDownLatch.countDown();
        });

        TransmittableThreadLocal<Integer> transmittableThreadLocal = new TransmittableThreadLocal<>();
        transmittableThreadLocal.set(1);
        log.info("get trans:{}",transmittableThreadLocal.get());
        Executor.executor.submit(()->{
            log.info("get trans:{}",transmittableThreadLocal.get());
            countDownLatch.countDown();
        });

        countDownLatch.await();

    }
}
