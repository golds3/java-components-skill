package com.hbx.study.redission.learn.lock;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

/**
 * 联锁-多把锁一起
 * 基于RedissonMultiLock
 */
@Service
public class MultiLockService extends BaseService {

    public void lock(String name1,String name2){
        RLock lock1 = redissonClient.getLock(name1);
        RLock lock2 = redissonClient.getLock(name2);
        RedissonMultiLock redissonMultiLock = new RedissonMultiLock(lock1, lock2);
        redissonMultiLock.lock();
    }
}
