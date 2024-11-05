package com.hbx.study.redission.learn.lock;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

/**
 * 可重入的公平锁
 * 先来先到
 */
@Service
public class FairLockService extends BaseService {

    public void lock(String name){
        RLock fairLock = redissonClient.getFairLock(name);
        fairLock.lock();
    }

}
