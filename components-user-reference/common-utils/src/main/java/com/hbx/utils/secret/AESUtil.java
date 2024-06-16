package com.hbx.utils.secret;

import lombok.experimental.UtilityClass;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * 对称加密工具类
 */
@UtilityClass
public class AESUtil {

    //AES 加密算法
    private static final String ALGORITHM = "AES";

    /**
     * 加密
     * @param data
     * @param key 密钥串
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * 解密
     * @param encryptedData
     * @param key 密钥串
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }

    public static void main(String[] args) throws Exception {
        String key = "1234567890123456"; // 16 字节密钥
        String originalString = "Sensitive Data";

        String encryptedString = AESUtil.encrypt(originalString, key);
        System.out.println("Encrypted String: " + encryptedString);

        String decryptedString = AESUtil.decrypt(encryptedString, key);
        System.out.println("Decrypted String: " + decryptedString);
    }
}
