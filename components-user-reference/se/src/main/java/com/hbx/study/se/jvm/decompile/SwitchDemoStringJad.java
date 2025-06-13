package com.hbx.study.se.jvm.decompile;

/**
 * javac SwitchDemoStringJad.java
 * jad SwitchDemoStringJad.class
 */
public class SwitchDemoStringJad {
    public static void main(String[] args) {
        String str = "world";
        switch (str){
            case "hello":
                System.out.println("hello");
                break;
            case "world":
                System.out.println("world");
                break;
            default:
                break;
        }
    }
}
