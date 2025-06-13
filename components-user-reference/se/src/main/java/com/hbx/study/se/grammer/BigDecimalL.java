package com.hbx.study.se.grammer;

import java.math.BigDecimal;

/**
 * 金额表示类
 */
public class BigDecimalL {

    /**
     * BigDecimal 能正确处理的原理
     */
    public void how(){
//        BigDecimal 使用数值+标位的方式表示一个数
        // 对于0.1 ，BigDecimal 表示的方式就是 value=1 scale=1，这样就避免小数精度缺失的问题

    }


    public static void main(String[] args) {
        System.out.println("使用示范。。。。。。");
        System.out.println("数值比较");
        BigDecimal b1 = new BigDecimal("1.0");
        BigDecimal b2 = new BigDecimal("1.00");
        System.out.println("b1==b2? use equals:"+b1.equals(b2)); // equals 会比较值和精度
        System.out.println("b1==b2? use compareTo:"+(0==b1.compareTo(b2)));
        System.out.println("浮点数的创建方式");
        b1 = new BigDecimal(0.1);//0.1对应的二进制是一个64位的无限循环数，所以他的精度就不是1这么简单了
        System.out.println("b1 scale:"+b1.scale());
        b1 = BigDecimal.valueOf(0.1);
        System.out.println("b1 scale:"+b1.scale());
        b2 = new BigDecimal("0.1");
        System.out.println("b2 scale:"+b2.scale());
        System.out.println("b1==b2?:"+(0==b1.compareTo(b2)));
        BigDecimal a1 = new BigDecimal("0.1");
        BigDecimal a2 = new BigDecimal("0.10");
        BigDecimal add = a2.add(a1);
        System.out.println(a1.scale()+" + "+a2.scale()+" = "+add);
    }
}
