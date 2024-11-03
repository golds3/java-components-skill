package com.hbx.study.redission.learn.base.string;

import com.hbx.study.redission.learn.BaseService;
import org.redisson.api.RKeys;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 对Redis中的key的操作
 * 都基于  org.redisson.api.RKeys实现
 *
 */
@Service
public class KeyOperate extends BaseService {

    /**
     * 获取当前数据库所有的keys
     * @return
     */
    public List<String> keys(){
        RKeys keys = redissonClient.getKeys();
        Iterable<String> keysList = keys.getKeys();
        List<String> lists = new ArrayList<>();
        keysList.forEach(item->{
            lists.add(item);
        });
        return lists;
    }

    /**
     * 删除某些key
     * @param keys
     * @return
     */
    public long deleteKey(String... keys){
        long deleted = redissonClient.getKeys().delete(keys);
        return deleted;
    }

}
