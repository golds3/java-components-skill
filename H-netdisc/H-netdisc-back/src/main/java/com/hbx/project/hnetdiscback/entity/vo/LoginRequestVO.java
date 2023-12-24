package com.hbx.project.hnetdiscback.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class LoginRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 7982767615746848669L;

    /**
     * 邮箱
     */
    private String email;
    /**
     * 图形验证码
     */
    private String checkCode;
    /**
     * 验证码编码
     */
    private String images;

    /**
     * 密码
     */
    private String password;
}
