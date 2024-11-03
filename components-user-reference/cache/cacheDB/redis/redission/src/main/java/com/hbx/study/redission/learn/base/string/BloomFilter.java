package com.hbx.study.redission.learn.base.string;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Service;

/**
 * 布隆过滤器
 */
@Service
public class BloomFilter extends BaseService {

    /**
     * 获取key的布隆过滤器
     * @param key
     * @return
     * @param <T>value的类型
     */
    public  <T>RBloomFilter<T> getBloomFilter(String key,long expectedInsertions, double falseProbability){
        RBloomFilter<T> bloomFilter = redissonClient.getBloomFilter(key);
        //初始化配置参数
        bloomFilter.tryInit(expectedInsertions,falseProbability);
        return bloomFilter;
    }
}
