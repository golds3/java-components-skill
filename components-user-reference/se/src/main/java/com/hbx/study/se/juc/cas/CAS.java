package com.hbx.study.se.juc.cas;

import lombok.Data;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 乐观锁
 * 自旋重试
 * 需要配合volatile使用，保证读取最新的值
 * Jvm创建对象的时候也使用了cas
 *
 * 原理，基于硬件的原子指令 cmpxchg解决并发问题，当cpu执行cmpxchg的时候，处理器会自动锁定总线，防止其他CPU访问共享变量，执行cmp和set，最后释放总线
 */
public class CAS {


    public static void main(String[] args) {
//        aba();
        abaSolve();
    }

    /**
     * ABA 问题
     * 虽然i经历aba，仍可以被线程2cas成功修改为3，但此时y以及变成3了，和线程2修改前的值不一致了
     */
    public static void aba(){
        @Data
        class Resource{
            private int i = 0;
            private int y = 0;
        }

        Resource resource = new Resource();
        resource.setI(1);
        resource.setY(2);

        //模拟aba问题
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            if (resource.getI()==1){
                try {
                    cyclicBarrier.await();
                    resource.setI(2);
                    resource.setY(3);
                    resource.setI(1);
                    countDownLatch.countDown();
                } catch (Exception e) {

                }
            }
        }).start();
        new Thread(() -> {
            if (resource.getI()==1){
                try {
                    cyclicBarrier.await();
                    countDownLatch.await();
                    resource.setI(2);
                    System.out.println("modify success,cur i:"+resource.getI());
                    System.out.println("now cur y is:"+resource.getY());
                } catch (Exception e) {

                }
            }else {
                System.out.println("i change,modify failed");
            }
        }).start();
    }

    /**
     * 使用AtomicStampedReference 基于版本号解决ABA问题
     */
    public static void abaSolve(){
        @Data
        class Resource{
            private int i = 0;
            private int y = 0;
        }

        Resource resource = new Resource();
        resource.setI(1);
        resource.setY(2);

        //模拟aba问题
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        AtomicStampedReference<Resource> stampedReference = new AtomicStampedReference<>(resource, 0);
        new Thread(() -> {
            if (resource.getI()==1){
                try {
                    cyclicBarrier.await();
                    Resource newResource = new Resource();
                    newResource.setI(2);
                    newResource.setY(3);
                    int preStamp = stampedReference.getStamp();
                    stampedReference.compareAndSet(resource,newResource, preStamp,preStamp+1);
                    Resource nnewResource = new Resource();
                    nnewResource.setI(1);
                    preStamp = stampedReference.getStamp();
                    stampedReference.compareAndSet(newResource,nnewResource, preStamp,preStamp+1);
                    countDownLatch.countDown();
                } catch (Exception e) {

                }
            }
        }).start();
        new Thread(() -> {
            if (resource.getI()==1){
                try {
                    int preStamp = stampedReference.getStamp();
                    cyclicBarrier.await();
                    countDownLatch.await();
                    Resource newResource = new Resource();
                    newResource.setI(2);
                    boolean b = stampedReference.compareAndSet(resource, newResource, preStamp, preStamp + 1);
                    if (!b){
                        System.out.println("modify failed");
                    }
                    System.out.println("modify success,cur i:"+resource.getI());
                    System.out.println("now cur y is:"+resource.getY());
                } catch (Exception e) {

                }
            }
        }).start();
    }
}
