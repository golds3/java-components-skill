package com.hbx.learn.jetchache.domain.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.CacheManager;
import com.alicp.jetcache.anno.*;
import com.alicp.jetcache.template.QuickConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class TestJetChache {

    @Autowired
    private CacheManager cacheManager;

    private Cache<String, Object > idUserCache;

    @PostConstruct
    public void init(){
        QuickConfig idQc = QuickConfig.newBuilder(":user:cache:id:")
                .cacheType(CacheType.BOTH)
                .expire(Duration.ofHours(2))
                .syncLocal(true)
                .build();
        idUserCache = cacheManager.getOrCreateCache(idQc);
    }

    public Object insert(String key,Object value){
        idUserCache.put(key,value);
        Object o = idUserCache.get(key);
        return o;
    }


    @Cached(name = ":user:cache:id::anno:",key = "#key")
    @CacheRefresh(refresh = 2, timeUnit = TimeUnit.SECONDS)
    public Object insertAnno(String key,Object value){
        System.out.println("i am in");
        return value;
    }


    @CacheUpdate(name = ":user:cache:id::anno:",key = "#key",value = "#value")
    public void updateAnno(String key,Object value){
    }

    @CacheInvalidate(name = ":user:cache:id::anno:",key = "#key")
    public void delAnno(String key){
    }
}
