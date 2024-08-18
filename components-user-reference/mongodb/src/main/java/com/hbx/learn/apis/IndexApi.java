package com.hbx.learn.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class IndexApi {
    @Autowired
    private MongoTemplate mongoTemplate;



}
