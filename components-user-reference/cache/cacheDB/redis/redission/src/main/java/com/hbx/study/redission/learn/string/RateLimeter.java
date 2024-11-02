package com.hbx.study.redission.learn.string;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.springframework.stereotype.Service;

/**
 * 基于Redis的限流器
 */
@Service
public class RateLimeter extends BaseService {
    /**
     * 滑动窗口策略限流
     * 每windowSize秒钟产生limit个令牌
     * @param key
     * @param limit
     * @param windowSize
     * @return
     */
    public Boolean tryAcquire(String key, int limit, int windowSize) {
        RRateLimiter rRateLimiter = redissonClient.getRateLimiter(key);

        if (!rRateLimiter.isExists()) {
            //设置限流器属性
            //最大流速 = 每windowSize秒钟产生limit个令牌
            rRateLimiter.trySetRate(RateType.OVERALL, limit, windowSize, RateIntervalUnit.SECONDS);
        }

        return rRateLimiter.tryAcquire();
    }
}
