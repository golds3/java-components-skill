package com.hbx.study.se.juc.cas;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Volatile 关键字
 * 特性:1.可见性 2.有序性（多线程下的）
 * 使用读写屏障避免指令重排序
 *
 * 典型案例---单例模式
 */
@Slf4j
public class Volatilel {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++){
            executorService.submit(()->{
                try {
                    Singleton.getInstance().call();
                } catch (InterruptedException e) {


                }
            });
        }
        executorService.shutdown();
    }
}
//todo 1.使用cas、饿汉、枚举、静态内部类实现
class Singleton {
    /**
     * volatile 关键字
     * 因为 instance = new Singleton(1); 涉及三大指令步骤，1.  分配内存空间 2.初始化对象 3.将instance指向分配的内存空间
     * 如果没有volatile 关键字,可能会进行指令冲排为 1-3-2，那么线程A执行到3的时候，线程B可能已经执行到1，那么线程B就会获取到instance的引用，然后调用call方法，就会出现nullPointerException
     */
    private static volatile Singleton instance;
    private int a ;
    private Singleton(int a){
        this.a = a;
    }

    /**
     * 使用锁实现安全的单例模式
     * @return
     * @throws InterruptedException
     */
    public static Singleton getInstance() throws InterruptedException {
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton(1);
                    TimeUnit.NANOSECONDS.sleep(10);
                }
            }
        }
        return instance;
    }

    /**
     * 基于cas  实现单例模式
     * @return
     * @throws InterruptedException
     */
    public static final AtomicReference<Singleton> INSTANCE = new AtomicReference<>();
    public static Singleton getInstanceCAS() throws InterruptedException {
        for (;;){
            Singleton singleton = INSTANCE.get();
            if (singleton != null){
                return instance;
            }
            singleton = new Singleton(1);
            if (INSTANCE.compareAndSet(null,singleton)){
                return INSTANCE.get();
            }
        }
    }


    public void call(){
        System.out.println("call:"+a);
    }
}