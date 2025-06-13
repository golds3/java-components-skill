package com.hbx.study.se.jvm;

import java.util.concurrent.TimeUnit;

/**
 * JIT即时编译
 * 热点代码直接编译成机器码，不再逐个解释执行
 * JIT会对热点代码进行逃逸分析，并进行代码优化
 * 1.锁消除
 * 2.标量替换、栈上分配
 * 3.方法内联
 */
public class JITL {

    /**
     * 锁消除
     */
    public static void lockJIT() {
        // lock无法逃逸，不存在竞争，可以进行锁消除
        Object lock = new Object();
        synchronized (lock) {
            try {
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {

            }
        }

    }

    /**
     * 标量替换，栈上分配
     * 如果对象不会逃逸，那么会进行优化，不创建对象，而是用基础数据类型进行替换对象内的属性
     * A a = new A(1,2); ===> int a = 1; int b = 2;
     */
    public static void scalarReplace() {
        A a = new A(1, 2);
        System.out.println("A.a=" + a.a + " A.b=" + a.b);
    }


    static class A {
        int a;
        int b;

        public A(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    /**
     * 方法内联
     * 对于一些简单的小方法，JIT会进行方法内联，将方法内代码直接替换到调用处，从而提高性能
     * int add = add(1, 2); ==》 int add = 1+2;
     */
    public static void inline() {
        int add = add(1, 2);
        System.out.println(add);
    }

    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long end;
        for (int i = 0; i < 100000; i++) {
            if (i % 100 == 0) {
                end = System.currentTimeMillis();
                System.out.println("100次用时" + (start - end));
                start = end;
            }
            lockJIT();
        }
        for (int i = 0; i < 1000; i++) {
            scalarReplace();
        }
        for (int i = 0; i < 1000; i++) {
            inline();
        }
    }
}
