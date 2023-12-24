package com.hbx.project.hnetdiscback.entity.constant;

/**
 * 常量实体类
 */
public class ApplicationConstant {
    /**
     * 图形验证码Redis key
     */
    public static String REDIS_BASEKEY_CAPTCHA = "CAPTCHA";

    /**
     * 邮箱验证码Redis Key
     */
    public static String REDIS_BASEKEY_VERCODE = "VERCODE";

    /**
     * 邮件主题
     */
    public static String EMAIL_SEND_SUBJECT = "H云盘";

    /**
     * 初始用户网盘空间
     */
    public static String USER_INIT_SPACE = "1TB";

    public static String USER_DISABLE_STATUS = "0";

    public static String USER_ENABLE_STATUS = "1";
}
