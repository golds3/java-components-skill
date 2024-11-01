package com.hbx.study.redission.learn;

import org.redisson.api.RBucket;
import org.springframework.stereotype.Service;

/**
 * [`RBucket`]Java对象是一种通用对象桶可以用来存放任意类型的对象。
 * 对应Redis 的string类型
 */
@Service
public class BucketTypeOperate extends BaseService{

    /**
     * string数据类型的 set方法
     * @param key
     * @param value
     */
    public void KVSet(String key,Object value){
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }



}
