package com.hbx.project.hnetdiscback.entity.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户信息VO
 */
@Data
public class UserInfoVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2000316433183129588L;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;
    /**
     * 已使用空间
     */
    private String useSpace;

    /**
     * 网盘容量
     */
    private String totalSpace;

    /**
     * 是否是管理员
     * 0-普通用户 1-管理员
     */
    private String admin;
}
