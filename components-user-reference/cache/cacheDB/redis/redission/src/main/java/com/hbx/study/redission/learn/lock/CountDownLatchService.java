package com.hbx.study.redission.learn.lock;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RCountDownLatch;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CountDownLatchService extends BaseService {

    public void lock(String name) throws InterruptedException {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                RCountDownLatch countDownLatch2 = redissonClient.getCountDownLatch(name);
                System.out.println("count--");
                countDownLatch2.countDown(); //计数器减一 ，如果变为零，通知所有的线程
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        RCountDownLatch countDownLatch = redissonClient.getCountDownLatch(name);

        countDownLatch.trySetCount(1); //设置计数器
        System.out.println("wait");
        countDownLatch.await(); //加锁，等到计数器变为0
//
        System.out.println("waked");
    }

}
