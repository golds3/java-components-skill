package com.hbx.study.se.juc.lock;

/**
 * Synchronized
 * 核心：基于 monitorenter 和 monitorexit 两个指令，以及对象的monitor(owner(占据锁的线程),wait set,entry set)实现
 * 特点：1.互斥 2.阻塞 3.可重入
 * 安全：1.原子性 2.可见性 3.有序性（同步快内的有序性）
 * 锁升级：无锁-->偏向锁-->轻量级锁(lockRecord)-->重量级锁(monitor)
 */
public class Syn {

    public static void main(String[] args) {


    }
}
