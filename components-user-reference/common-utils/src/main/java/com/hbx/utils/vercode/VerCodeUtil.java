package com.hbx.utils.vercode;


import com.hbx.utils.vercode.factory.VerCodeStrategyFactory;
import com.hbx.utils.vercode.strategy.VerCodeStrategy;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 生成验证码工具类
 */
public class VerCodeUtil {
    private VerCodeStrategy strategy;

    public VerCodeUtil(VerCodeStrategyFactory factory) {
        this.strategy = factory.createStrategy();
    }

    /**
     * 直接把验证码图片写入页面
     *
     * @param code 验证码
     */
    public void generateVerCode(HttpServletResponse response, String code) throws IOException {
        this.strategy.generateVerCode(response, code);
    }

    /**
     * 生成验证码图片的Base64编码，给前端使用
     *
     * @param code 验证码
     * @return
     */
    public String getVerCodeBase64(String code) throws IOException {
        return this.strategy.getVerCodeBase64(code);
    }


}
