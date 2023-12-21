package com.hbx.project.hnetdiscback;


import com.hbx.project.hnetdiscback.mapper.UserInfoMapper;
import com.hbx.project.hnetdiscback.utils.EmailUtils;
import com.hbx.project.hnetdiscback.utils.VerCodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class HNetdiscBackApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void testRedis(){
        stringRedisTemplate.opsForValue().set("k1","v1",1, TimeUnit.MINUTES);
    }
    @Test
    public void testUtils(){
        String s = VerCodeUtil.generalAllCharCode(6);
        System.out.println(s);
    }
    @Test
    public void testUserInfo(){
        Integer i = userInfoMapper.queryCountByEmail("1");
        System.out.println(i);
    }

    @Test
    public void testEmailSend(){
        EmailUtils.sendEmailTextContext("您的验证码是 123456 验证码1分钟内有效,请谨慎保管","1499091922@qq.com");
    }

}
