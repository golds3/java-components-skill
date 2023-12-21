package com.hbx.project.hnetdiscback.utils;

import java.util.Random;

/**
 * 验证码生成工具类
 */
public class VerCodeUtil {

    //字母数据集
    private static char[] c;
    //数字集
    private static char[] numberCharacter;
    //数字+大小写字母数据集
    private static char[] allCharacter;

    static {
        String i = "1234567890";
        numberCharacter = i.toCharArray();
        String s = "qwertyuiopasdfghjklzxcvbnm";
        String S = s.toUpperCase();
        c = (s + S).toCharArray();
        allCharacter = (s + S + i).toCharArray();
    }

    public static String generalAllCharCode(int length) {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();
        for (int k = 0; k < length; k++) {
            int index = rd.nextInt(allCharacter.length);//随机获取数组长度作为索引
            sb.append(allCharacter[index]);//循环添加到字符串后面
        }
        return sb.toString();
    }

}
