package com.hbx.study.se.jvm.aot;

/**
 * 使用AOT提前编译
 * javac AOTClass.java
 *
 * curl -s "https://get.sdkman.io" | bash
 * 使用GraalVM AOT编译
 * # 使用 SDKMAN 安装 GraalVM
 * sdk install java  21.0.2-graalce
 *
 *  * 1.cd /Users/ham/Desktop/project/back/java/java-components-skill/components-user-reference/se/src/main/java
 *  * 2.javac com/hbx/study/se/jvm/aot/AOTClass.java
 *  * 3.native-image -cp . com.hbx.study.se.jvm.aot.AOTClass
 *  * 4. ./com.hbx.study.se.jvm.aot.aotclass
 */
public class AOTClass {

    public static void main(String[] args) {
        System.out.println("start finished!");
    }
}
