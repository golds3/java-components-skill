package com.hbx.utils.uuid;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UUIDUtil {
    /**
     * 生成不带连字符的UUID字符串
     * @return UUID字符串
     */
    public static String generateUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 生成带连字符的标准UUID字符串
     * @return 标准UUID字符串 '-'
     */
    public static String generateStandardUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        String uuid1 = UUIDUtil.generateUUID();
        System.out.println("UUID without hyphens: " + uuid1);

        String uuid2 = UUIDUtil.generateStandardUUID();
        System.out.println("Standard UUID: " + uuid2);
    }
}
