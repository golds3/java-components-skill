package com.hbx.project.hnetdiscback.service;

import com.hbx.project.hnetdiscback.entity.vo.AopResponse;
import com.hbx.project.hnetdiscback.entity.vo.EmailCodeRequestVO;
import com.hbx.project.hnetdiscback.entity.vo.RegisterRequestVO;

/**
 * 登录注册Service
 */
public interface LoginService {

    AopResponse<String> getVerificationCode();

    AopResponse<Object> sendEmailVerificationCode(EmailCodeRequestVO requestVO) throws Exception;

    AopResponse<Object> register(RegisterRequestVO requestVO) throws Exception;
}
