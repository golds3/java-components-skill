package com.hbx.study.mybatis3.mapper;

import com.hbx.study.mybatis3.pojo.SignInf;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AIMapper {
    public List<SignInf> getSignInf();
}
