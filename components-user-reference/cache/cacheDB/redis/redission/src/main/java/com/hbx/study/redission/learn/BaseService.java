package com.hbx.study.redission.learn;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {

    @Autowired
    protected RedissonClient redissonClient;
}
