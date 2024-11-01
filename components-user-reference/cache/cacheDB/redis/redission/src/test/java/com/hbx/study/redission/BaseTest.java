package com.hbx.study.redission;

import com.hbx.study.redission.learn.InsertCase;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = MyRedission.class)
//@ActiveProfiles("test")
public class BaseTest {
    @Autowired
    private InsertCase insertCase;
    @Test
    public void testInsertKV(){
        insertCase.insertKV("test","hh");

    }
}
