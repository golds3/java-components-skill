package com.hbx.study.se.grammer.spi;

public class Cat implements IShout{
    @Override
    public void shout() {
        System.out.println("喵喵喵");
    }
}
