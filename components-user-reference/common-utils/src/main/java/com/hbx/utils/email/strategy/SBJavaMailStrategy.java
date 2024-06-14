package com.hbx.utils.email.strategy;

import com.hbx.utils.email.dto.MailSendDTO;
import jakarta.annotation.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 使用JavaMail Api发送邮件
 * Spring boot环境下可以使用starter替代 {@link SBJavaMailStrategy}
 */
@Service
public class SBJavaMailStrategy {
    @Resource
    private JavaMailSender mailSender;

    public void send(MailSendDTO mailSendDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailSendDTO.getTo());
        message.setFrom(mailSendDTO.getFrom());
        message.setSubject(mailSendDTO.getSubject());
        message.setText(mailSendDTO.getText());
        mailSender.send(message);
    }
}
