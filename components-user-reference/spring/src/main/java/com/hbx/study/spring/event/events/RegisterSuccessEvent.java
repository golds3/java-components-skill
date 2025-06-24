package com.hbx.study.spring.event.events;

import org.springframework.context.ApplicationEvent;

public class RegisterSuccessEvent extends ApplicationEvent {
    public RegisterSuccessEvent(Object source) {
        super(source);
    }
}
