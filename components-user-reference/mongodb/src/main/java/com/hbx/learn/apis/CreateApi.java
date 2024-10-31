package com.hbx.learn.apis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * 插入数据API
 */
@Component
@Slf4j
public class CreateApi {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入一条数据
     */
    public void insertOne(Object o){
        mongoTemplate.insert(o);
        log.info("插入document：{}",o);
    }

}
