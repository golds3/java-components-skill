package com.hbx.study.redission.learn.lock;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

/**
 * 基于RLock的可重入锁
 */
@Service
public class RLockService extends BaseService {

    public void lock(String name){
        RLock lock = redissonClient.getLock(name);
        lock.lock();
    }



}
