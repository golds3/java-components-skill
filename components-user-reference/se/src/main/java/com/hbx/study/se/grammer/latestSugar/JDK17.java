package com.hbx.study.se.grammer.latestSugar;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Switch 表达式的优化
 */
public class JDK17 {

    public static String switchExpression(Object o) {
        String f = switch (o) {
            case Integer i -> {
                yield "integer" + i;
            }
            case String s -> {
                yield "string" + s;
            }
            case Double d -> {
                yield "double" + d;
            }
            default -> {
                yield o.toString();
            }
        };
        return f;
    }
    public static void main(String[] args) {
        System.out.println(switchExpression(1));
        System.out.println(switchExpression("1"));
        System.out.println(switchExpression(1.0));
        System.out.println(switchExpression(Long.valueOf(1)));
        var a = new Integer[]{1,2,3};
    }
}
