package com.hbx.study.se.grammer.multiImpl;

public interface B {
    default void fn() {
        System.out.println("B");
    }

}
