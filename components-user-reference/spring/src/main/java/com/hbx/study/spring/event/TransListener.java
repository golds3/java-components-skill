package com.hbx.study.spring.event;

import com.hbx.study.spring.event.events.TransEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.*;

@Component
@Slf4j
public class TransListener{
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleTransEvent(TransEvent event) {
        log.info("TransListener AFTER_COMMIT: {}", event.getSource());
    }

    @TransactionalEventListener(phase = BEFORE_COMMIT)
    public void handleTransEvent2(TransEvent event) {
        log.info("TransListener BEFORE_COMMIT: {}", event.getSource());
    }

    @TransactionalEventListener(phase = AFTER_ROLLBACK)
    public void handleTransEvent3(TransEvent event) {
        log.info("TransListener AFTER_ROLLBACK: {}", event.getSource());
    }
    @TransactionalEventListener(phase = AFTER_COMPLETION)
    public void handleTransEvent4(TransEvent event) {
        log.info("TransListener AFTER_COMPLETION: {}", event.getSource());
    }

}
