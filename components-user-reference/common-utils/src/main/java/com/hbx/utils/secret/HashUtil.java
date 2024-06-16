package com.hbx.utils.secret;

import lombok.experimental.UtilityClass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 哈希加密工具类，不可逆
 * 使用更安全的SHA-256算法
 */
@UtilityClass
public class HashUtil {

    /**
     * 对密码进行加盐哈希处理
      * @param password
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String hash(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt); // 加盐
        byte[] hashedPassword = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = "userPassword123";

        // 生成盐值
        byte[] salt = new byte[0];

        // 哈希加盐处理密码
        String hashedPassword = hash(password, salt);

        System.out.println("Original Password: " + password);
        System.out.println("Salt: " + Base64.getEncoder().encodeToString(salt));
        System.out.println("Hashed Password: " + hashedPassword);
    }

}
