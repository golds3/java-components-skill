package com.hbx.study.se.juc.lock.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 同步器框架
 * concurrent 包下的基本都是基于同步器框架实现的
 *
 * 核心： 1.FIFO队列（元素锁Node对象）（同步队列、条件队列） 2.state 状态变量(volatile)
 * state=1 --> 对象锁已被获取，使用cas 进行state的赋值
 * state>1 --> 锁重入
 * 获取锁失败，将线程包装为Node对象，加入FIFO队列尾部
 */
public class AQS {
}


class MyLock  implements Lock{
    private static class Sync extends AbstractQueuedSynchronizer{

    }
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
