package com.hbx.project.hnetdiscback.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class RegisterRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -1339951187789542047L;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 图形验证码
     */
    private String checkCode;

    /**
     * 邮箱验证码
     */
    private String emailCode;

    /**
     * 验证码编码
     */
    private String images;
}
