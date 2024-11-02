package com.hbx.study.redission.learn.set;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RSet;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RSortedSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Redis Set 类型操作
 * 基于RSet实现
 */
@Service
public class SetType extends BaseService {

    public void set(String key,Object value){
        RSet<Object> set = redissonClient.getSet(key);
        set.add(value);
    }

    public void sortSet(String key, Integer value, Comparator<Integer> comparator){
        RSortedSet<Integer> sortedSet = redissonClient.getSortedSet(key);
        sortedSet.trySetComparator(comparator);
        sortedSet.add(value);
    }
}
