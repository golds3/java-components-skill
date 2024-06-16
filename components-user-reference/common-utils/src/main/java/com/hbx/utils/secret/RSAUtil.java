package com.hbx.utils.secret;

import lombok.experimental.UtilityClass;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * 非对称加密工具类
 */
@UtilityClass
public class RSAUtil {
    private static final String ALGORITHM = "RSA";

    /**
     * 生成公、私钥 对
     * @return
     * @throws Exception
     */
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 加密
     * @param data
     * @param publicKey 公钥
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * 解密
     * @param encryptedData
     * @param privateKey 私钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = RSAUtil.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String originalString = "Sensitive Data";

        String encryptedString = RSAUtil.encrypt(originalString, publicKey);
        System.out.println("Encrypted String: " + encryptedString);

        String decryptedString = RSAUtil.decrypt(encryptedString, privateKey);
        System.out.println("Decrypted String: " + decryptedString);
    }
}
