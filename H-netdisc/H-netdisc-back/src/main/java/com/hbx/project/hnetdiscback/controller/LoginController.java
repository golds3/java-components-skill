package com.hbx.project.hnetdiscback.controller;

import com.hbx.project.hnetdiscback.entity.vo.*;
import com.hbx.project.hnetdiscback.service.LoginService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api")
@MapperScan("com.hbx.project.hnetdiscback.mapper")
public class LoginController {
    @Autowired
    private LoginService loginService;


    /**
     * 获取图形验证码验证码
     *
     * @return
     */
    @GetMapping("/checkCode")
    public AopResponse<String> getVerificationCode() {
        return loginService.getVerificationCode();
    }

    /**
     * 获取邮箱验证码
     * @param requestVO
     * @return
     * @throws Exception
     */
    @PostMapping("/sendEmailCode")
    public AopResponse<Object> sendEmailVerificationCode(@RequestBody EmailCodeRequestVO requestVO) throws Exception {
        return loginService.sendEmailVerificationCode(requestVO);
    }

    @PostMapping("/register")
    public AopResponse<Object> register(@RequestBody RegisterRequestVO requestVO) throws Exception {
        return loginService.register(requestVO);
    }

    @PostMapping("login")
    public AopResponse<UserInfoVO> login(@RequestBody LoginRequestVO requestVo) throws Exception {
        return loginService.login(requestVo);
    }
}
