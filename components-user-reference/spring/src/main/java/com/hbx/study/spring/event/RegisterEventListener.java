package com.hbx.study.spring.event;

import com.hbx.study.spring.event.events.RegisterSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegisterEventListener {
    @EventListener(RegisterSuccessEvent.class)
    public void handleRegisterSuccessEvent(RegisterSuccessEvent event) {
        log.info("监听到事件：" + event.getSource());
    }

    @EventListener(RegisterSuccessEvent.class)
    @Async
    public void handleRegisterSuccessEvent2(RegisterSuccessEvent event) {
        log.info("监听到事件2：" + event.getSource());
    }
}
