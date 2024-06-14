package com.hbx.utils.email.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 发送邮件的信息实体类
 */
@Data
@Builder
public class MailSendDTO {
    /**
     * 收件人电子邮箱
     */
    private String to;
    /**
     * 发件人电子邮箱(展示用)
     */
    private String from;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件正文
     */
    private String text;

    //======= 如果使用Spring Boot的方式，下面这些属性配置在yml即可

    /**
     * 邮件平台系统host
     */
    private String host;
    /**
     * 邮件平台端口
     */
    private int port;
    /**
     * 发件人登陆名
     */
    private String username;
    /**
     * 发件人密码
     */
    private String password;

    /**
     * 是否需要对SMTP服务器进行身份验证。
     */
    private boolean auth;
    /**
     * 是否使用STARTTLS命令来启动TLS加密连接
     */
    private boolean starttlsEnable;

}
