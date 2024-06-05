package com.hbx.utils.vercode.strategy;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 生产验证码策略接口
 */
public interface VerCodeStrategy {



    /**
     * 直接把验证码图片写入页面
     *
     * @param code 验证码
     */
    void generateVerCode(HttpServletResponse response, String code) throws IOException;

    /**
     * 生成验证码图片的Base64编码，给前端使用
     *
     * @param code 验证码
     * @return
     */
    String getVerCodeBase64(String code) throws IOException;


}
