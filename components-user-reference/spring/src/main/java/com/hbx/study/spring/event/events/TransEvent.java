package com.hbx.study.spring.event.events;

import org.springframework.context.ApplicationEvent;

public class TransEvent extends ApplicationEvent {
    public TransEvent(Object source) {
        super(source);
    }
}
