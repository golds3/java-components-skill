package com.hbx.project.hnetdiscback.service.impl;

import com.hbx.project.hnetdiscback.entity.constant.ApplicationConstant;
import com.hbx.project.hnetdiscback.entity.pojo.UserInfoDO;
import com.hbx.project.hnetdiscback.entity.vo.AopResponse;
import com.hbx.project.hnetdiscback.entity.vo.EmailCodeRequestVO;
import com.hbx.project.hnetdiscback.entity.vo.RegisterRequestVO;
import com.hbx.project.hnetdiscback.mapper.UserInfoMapper;
import com.hbx.project.hnetdiscback.service.LoginService;
import com.hbx.project.hnetdiscback.utils.EmailUtils;
import com.hbx.project.hnetdiscback.utils.VerCodeUtil;

import com.wf.captcha.SpecCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 登录注册Service
 */

@Slf4j
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    //    private static final TaskExecutor taskExecutor;
//    static {
//        // 在静态块中初始化 ThreadPoolTaskExecutor
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.initialize();
//        taskExecutor = executor;
//    }
    @Override
    public AopResponse<String> getVerificationCode() {
        //生成图形验证码
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String images = specCaptcha.toBase64();
        //获取图形验证码中的验证码文本
        String verCode = specCaptcha.text().toLowerCase();
        //验证码+图片编码作为唯一标识，防止多次请求覆盖有效的验证码
        redisTemplate.opsForValue().set(ApplicationConstant.REDIS_BASEKEY_CAPTCHA + ":" + images, verCode, 5, TimeUnit.MINUTES);
        return new AopResponse<String>().success(images);
    }

    @Override
    public AopResponse<Object> sendEmailVerificationCode(EmailCodeRequestVO requestVO) throws Exception {
        String email = requestVO.getEmail();
        //校验图形验证码
        checkCaptcha(requestVO.getCheckCode(), requestVO.getImages());
        //校验邮箱信息
        checkUserByEmail(email, requestVO.getType());
        //生成验证码
        String verCode = VerCodeUtil.generalAllCharCode(6);
        //发送邮箱
        sendEmail(verCode, email);

        return new AopResponse<>().success();
    }

    @Override
    @Transactional
    public AopResponse<Object> register(RegisterRequestVO requestVO) throws Exception {
        //校验图形验证码
        checkCaptcha(requestVO.getCheckCode(), requestVO.getImages());
        //校验邮箱验证码
        checkEmailCode(requestVO.getEmailCode(), requestVO.getEmail());
        //入库
        UserInfoDO userInfoDO = buildUserInfo(requestVO);
        Integer i =  userInfoMapper.insert(userInfoDO);
        return new AopResponse<>().success();
    }

    private UserInfoDO buildUserInfo(RegisterRequestVO requestVO) {
        UserInfoDO userInfoDO = new UserInfoDO();
        userInfoDO.setJoinTime(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        userInfoDO.setNickName(requestVO.getNickName());
        userInfoDO.setEmail(requestVO.getEmail());
        userInfoDO.setUseSpace("0");
        userInfoDO.setTotalSpace(ApplicationConstant.USER_INIT_SPACE);
        //密码MD5加密
        String password = DigestUtils.md5Hex(requestVO.getPassword());
        userInfoDO.setPassword(password);
        userInfoDO.setUserId(VerCodeUtil.generalAllCharCode(13));
        return userInfoDO;
    }

    private void checkEmailCode(String emailCode, String email) throws Exception {
        String ordCheckCode = redisTemplate.opsForValue().get(ApplicationConstant.REDIS_BASEKEY_CAPTCHA + ":" + email);
        if (ordCheckCode == null) {
            throw new Exception("邮箱验证码已过期，请刷新");
        } else if (!StringUtils.equals(emailCode, ordCheckCode)) {
            throw new Exception("邮箱验证码输入错误");
        }
    }

    private void sendEmail(String verCode, String email) {
        String context = "您的验证码是 " + verCode + " 验证码1分钟内有效,请谨慎保管";
//        // TODO: 2023/12/21 优化使用多线程的方法
//        taskExecutor.execute(()->{
        EmailUtils.sendEmailTextContext(context, email);
        //保存验证码
        redisTemplate.opsForValue().set(ApplicationConstant.REDIS_BASEKEY_VERCODE + ":" + email, verCode, 1, TimeUnit.MINUTES);
//        });
    }


    private void checkCaptcha(String checkCode, String images) throws Exception {
        String ordCheckCode = redisTemplate.opsForValue().get(ApplicationConstant.REDIS_BASEKEY_CAPTCHA + ":" + images);
        if (ordCheckCode == null) {
            throw new Exception("图形验证码已过期，请刷新");
        } else if (!StringUtils.equals(checkCode, ordCheckCode)) {
            throw new Exception("图形验证码输入错误");
        }
    }

    private void checkUserByEmail(String email, int type) throws Exception {
        Integer count = userInfoMapper.queryCountByEmail(email);
        if (count > 0 && type == 0) {
            //注册流程
            throw new Exception("该邮箱已注册！");
        } else if (count <= 0 && type == 1) {
            //找回密码流程
            throw new Exception("该邮箱未注册");
        }
    }
}




