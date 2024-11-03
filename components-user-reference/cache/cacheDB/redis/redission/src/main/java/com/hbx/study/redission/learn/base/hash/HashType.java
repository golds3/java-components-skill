package com.hbx.study.redission.learn.base.hash;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RMap;
import org.springframework.stereotype.Service;

/**
 * redis hash类型操作
 * 基于RMap实现
 */
@Service
public class HashType extends BaseService {

    public void set(String key, Object field, Object value, boolean override) {
        RMap<Object, Object> map = redissonClient.getMap(key);
        if (override) {
            map.put(field, value);
        } else {
            map.putIfAbsent(field, value);
        }
    }

    public boolean removeField(String key, Object field) {
        return redissonClient.getMap(key).remove(field) != null;
    }

    public Object getFieldValue(String key, Object field) {
        return redissonClient.getMap(key).get(field);
    }
}
