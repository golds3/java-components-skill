package com.hbx.study.redission.learn.lock;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

/**
 * 红锁 基于RedissonRedLock
 */
@Service
public class RedLockService extends BaseService {
    public void lock(String name){
        RLock lock = redissonClient.getLock(name);
        RedissonRedLock redissonRedLock = new RedissonRedLock(lock);
        redissonRedLock.lock();
    }
}
