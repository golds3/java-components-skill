package com.hbx.study.redission.learn.lock;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RSemaphore;
import org.springframework.stereotype.Service;

@Service
public class SemaphoreService extends BaseService {

    public void lock(String name) throws InterruptedException {
        RSemaphore semaphore = redissonClient.getSemaphore(name);

        semaphore.acquire(); //lock  with wait
        semaphore.acquire(2); //get 2 lock
        semaphore.tryAcquire(); //lock without wait
        semaphore.release(); //unlock
    }
}
