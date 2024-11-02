package com.hbx.study.redission.learn.string;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RBucket;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * [`RBucket`]Java对象是一种通用对象桶可以用来存放任意类型的对象。
 * 对应Redis 的string类型
 */
@Service
public class BucketTypeOperate extends BaseService {

    /**
     * string数据类型的 set方法
     * @param override 是否覆盖
     * @param duration 过期时间
     * @param key
     * @param value
     */
    public void KVSet(String key, Object value, Duration duration, boolean override){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        if (override){
            if (duration!=null){
                bucket.set(value,duration);
            }else {
                bucket.set(value);
            }
        }else{
            if (duration!=null){
                bucket.setIfAbsent(value,duration);
            }else {
                bucket.setIfAbsent(value);
            }
        }
    }




}
