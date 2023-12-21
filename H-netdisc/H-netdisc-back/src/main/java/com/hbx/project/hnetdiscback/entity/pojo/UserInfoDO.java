package com.hbx.project.hnetdiscback.entity.pojo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户表实体对象
 */
@Data
public class UserInfoDO implements Serializable {
    @Serial
    private static final long serialVersionUID = -3564887179307209492L;

    private String userId;
    private String email;
    private String nickName;
    private String qqOpenId;
    private String qqAvatar;
    private String password;
    private String joinTime;
    private String lastLoginTime;
    private String status;
    private String useSpace;
    private String totalSpace;
    private String admin;

}
