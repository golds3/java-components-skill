package com.hbx.study.redission;

import com.hbx.study.redission.learn.BaseService;
import com.hbx.study.redission.learn.BucketTypeOperate;
import com.hbx.study.redission.learn.InsertCase;
import com.hbx.study.redission.learn.KeyOperate;
import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = MyRedission.class)
//@ActiveProfiles("test")
public class BaseTest {
    @Autowired
    private InsertCase insertCase;
    @Autowired
    private RedissonClient redissonClient;
    @Test
    public void testInsertKV(){
        RBucket<Object> bucket = redissonClient.getBucket("buck2");
        Object o = bucket.get();
        System.out.println(o);

    }

    @Autowired
    private KeyOperate keyOperate;
    @Test
    public void testKeys(){
        List<String> keys = keyOperate.keys();
        System.out.println(keys);
    }

    @Test
    public void testDeleteKey(){
        keyOperate.deleteKey("test2");
    }


    @Autowired
    private BucketTypeOperate bucketTypeOperate;
    @Test
    public void kvSet(){
        bucketTypeOperate.KVSet("buck2", Arrays.asList("1",2,3.0));
    }

}
