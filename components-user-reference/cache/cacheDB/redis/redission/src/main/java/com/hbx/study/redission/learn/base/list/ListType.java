package com.hbx.study.redission.learn.base.list;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RList;

/**
 * Redis list 类型操作
 * 基于RList实现
 */
public class ListType extends BaseService {
    public void listKey(String key){
        RList<Object> list = redissonClient.getList(key);
    }
}
