package com.hbx.project.hnetdiscback.service;

import com.hbx.project.hnetdiscback.entity.vo.*;

/**
 * 登录注册Service
 */
public interface LoginService {

    AopResponse<String> getVerificationCode();

    AopResponse<Object> sendEmailVerificationCode(EmailCodeRequestVO requestVO) throws Exception;

    AopResponse<Object> register(RegisterRequestVO requestVO) throws Exception;

    AopResponse<UserInfoVO> login(LoginRequestVO requestVo) throws Exception;
}
