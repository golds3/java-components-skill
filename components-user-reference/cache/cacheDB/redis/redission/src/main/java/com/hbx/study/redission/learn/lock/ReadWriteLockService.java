package com.hbx.study.redission.learn.lock;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RReadWriteLock;
import org.springframework.stereotype.Service;

/**
 * 读写锁 - 基于RReadWriteLock
 */
@Service
public class ReadWriteLockService extends BaseService {
    public void lock(String name){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(name);
        readWriteLock.readLock().lock();
        readWriteLock.writeLock().lock();
    }

}
