package com.hbx.study.se.jvm.decompile;

/**
 * javac SwitchDemoStringCfr.java
 * java -jar cfr_0125.jar SwitchDemoStringCfr.class --decodestringswitch false
 */
public class SwitchDemoStringCfr {
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
