package com.hbx.project.hnetdiscback.utils;

import com.hbx.project.hnetdiscback.entity.constant.ApplicationConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

/**
 * 邮件发送工具类
 */
@Slf4j
public class EmailUtils {

    private static JavaMailSender mailSender;

    static {
        mailSender = SpringContextUtil.getBean(JavaMailSender.class);
    }


    public static void sendEmailTextContext(String context,String distEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1499091922@qq.com");
        message.setTo(distEmail);
        message.setSubject(ApplicationConstant.EMAIL_SEND_SUBJECT);
        message.setText(context);
        log.info("开始发送邮件，收件人：{}，邮件信息：{}",distEmail,context);
        mailSender.send(message);
    }
}
