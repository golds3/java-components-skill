package com.hbx.utils.email;


import com.hbx.utils.email.dto.MailSendDTO;
import com.hbx.utils.email.factory.MailStrategyFactory;
import com.hbx.utils.email.strategy.MailStrategy;

import java.io.IOException;

/**
 * 生成验证码工具类
 */
public class EmailSendUtil {
    private MailStrategy strategy;

    public EmailSendUtil(MailStrategyFactory factory) {
        this.strategy = factory.createStrategy();
    }

    /**
     * 发送邮件
     *
     * @param mailSendDTO 邮件信息实体
     */
    public void send(MailSendDTO mailSendDTO) throws IOException {
        this.strategy.send(mailSendDTO);
    }




}
