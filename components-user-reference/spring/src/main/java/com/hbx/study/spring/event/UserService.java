package com.hbx.study.spring.event;

import com.hbx.study.spring.event.events.TransEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * todo 需要引入数据库
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    private ApplicationContext applicationContext;
    @Transactional
    public void trans() {
        log.info("trans start");
        applicationContext.publishEvent(new TransEvent("trans event"));
        throw new RuntimeException("trans error");
    }
}
