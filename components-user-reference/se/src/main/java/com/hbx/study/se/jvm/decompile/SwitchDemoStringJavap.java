package com.hbx.study.se.jvm.decompile;
/**
 * 反编译switch的源码
 * javac SwitchDemoStringJavap.java
 * javap -c SwitchDemoStringJavap.class
 */
public class SwitchDemoStringJavap {
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

    /**
     * Compiled from "SwitchDemoString.java"
     * public class com.hbx.study.se.jvm.decompile.SwitchDemoString {
     *   public com.hbx.study.se.jvm.decompile.SwitchDemoString();
     *     Code:
     *        0: aload_0
     *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
     *        4: return
     *
     *   public static void main(java.lang.String[]);
     *     Code:
     *        0: ldc           #7                  // String world
     *        2: astore_1
     *        3: aload_1
     *        4: astore_2
     *        5: iconst_m1
     *        6: istore_3
     *        7: aload_2
     *        8: invokevirtual #9                  // Method java/lang/String.hashCode:()I
     *       11: lookupswitch  { // 2
     *               99162322: 36
     *              113318802: 50
     *                default: 61
     *           }
     *       36: aload_2
     *       37: ldc           #15                 // String hello
     *       39: invokevirtual #17                 // Method java/lang/String.equals:(Ljava/lang/Object;)Z
     *       42: ifeq          61
     *       45: iconst_0
     *       46: istore_3
     *       47: goto          61
     *       50: aload_2
     *       51: ldc           #7                  // String world
     *       53: invokevirtual #17                 // Method java/lang/String.equals:(Ljava/lang/Object;)Z
     *       56: ifeq          61
     *       59: iconst_1
     *       60: istore_3
     *       61: iload_3
     *       62: lookupswitch  { // 2
     *                      0: 88
     *                      1: 99
     *                default: 110
     *           }
     *       88: getstatic     #21                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *       91: ldc           #15                 // String hello
     *       93: invokevirtual #27                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *       96: goto          110
     *       99: getstatic     #21                 // Field java/lang/System.out:Ljava/io/PrintStream;
     *      102: ldc           #7                  // String world
     *      104: invokevirtual #27                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *      107: goto          110
     *      110: return
     * }
     */
}
