package com.hbx.study.se.grammer.multiImpl;

/**
 * 由于接口允许有默认方法，所以对于D，就相当于是菱形继承了，
 * B，C都有fn这个方法，D如果去调用就会有疑义，所以需要Java要求D必须重写fn方法
 */
public class D implements B,C{

    /**
     * B，C都有fn这个方法，D如果去调用就会有疑义，所以需要Java要求D必须重写fn方法
     */
    @Override
    public void fn() {
        B.super.fn();
    }


    public static void main(String[] args) {
        new D().fn();
    }
}
