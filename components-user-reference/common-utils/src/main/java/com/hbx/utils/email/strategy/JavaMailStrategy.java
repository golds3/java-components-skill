package com.hbx.utils.email.strategy;

import com.hbx.utils.email.dto.MailSendDTO;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * 使用JavaMail Api发送邮件
 * Spring boot环境下可以使用starter替代 {@link SBJavaMailStrategy}
 */
public class JavaMailStrategy implements MailStrategy{

    @Override
    public void send(MailSendDTO mailSendDTO) {
        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.put("mail.smtp.host", mailSendDTO);
        properties.put("mail.smtp.port", String.valueOf(mailSendDTO.getPort())); // 端口号
        properties.put("mail.smtp.auth", String.valueOf(mailSendDTO.isAuth()));
        properties.put("mail.smtp.starttls.enable", String.valueOf(mailSendDTO.isStarttlsEnable()));

        // 获取默认的Session对象
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailSendDTO.getUsername(), mailSendDTO.getPassword());
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(mailSendDTO.getFrom()));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailSendDTO.getTo()));

            // Set Subject: 头部头字段
            message.setSubject(mailSendDTO.getSubject());

            // 设置消息体
            message.setText(mailSendDTO.getText());

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
