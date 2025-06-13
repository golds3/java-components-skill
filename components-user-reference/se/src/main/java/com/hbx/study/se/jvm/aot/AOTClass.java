package com.hbx.study.se.jvm.aot;

/**
 * 使用AOT提前编译
 * javac AOTClass.java
 *
 * curl -s "https://get.sdkman.io" | bash
 * 使用GraalVM AOT编译
 * # 使用 SDKMAN 安装 GraalVM
 * sdk install java 21.0.3-graal
 *
 * # 安装 native-image 插件（替代 jaotc）
 * gu install native-image
 *
 * native-image AOTClass
 */
public class AOTClass {

    public static void main(String[] args) {
        System.out.println("start finished!");
    }
}
