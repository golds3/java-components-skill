package com.hbx.project.hnetdiscback.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 发送邮箱验证码请求对象
 */
@Data
public class EmailCodeRequestVO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2506845771900924242L;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 图形验证码
     */
    private String checkCode;

    /**
     * 请求类型  0:注册 1-找回密码
     */
    private int type;

    /**
     * 验证码编码
     */
    private String images;

}
