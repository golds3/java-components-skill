package com.hbx.utils.email.strategy;

import com.hbx.utils.email.dto.MailSendDTO;

import java.io.IOException;

/**
 * 发送邮件策略类
 */
public interface MailStrategy {

    void send(MailSendDTO mailSendDTO) throws IOException;
}
