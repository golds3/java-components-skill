package com.hbx.project.hnetdiscback.mapper;

import com.hbx.project.hnetdiscback.entity.pojo.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper {

    Integer queryCountByEmail(@Param("email") String email);

    Integer insert(UserInfoDO userInfoDO);
}
