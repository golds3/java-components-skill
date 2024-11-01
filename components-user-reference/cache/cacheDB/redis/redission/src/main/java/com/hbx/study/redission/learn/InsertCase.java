package com.hbx.study.redission.learn;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InsertCase extends BaseService {

    public void insertKV(String key,String value){
        redissonClient.getBucket(key).set(value);
    }

}
