package com.hbx.utils.email.factory;

import com.hbx.utils.email.strategy.JavaMailStrategy;
import com.hbx.utils.email.strategy.MailStrategy;

public class JavaMailStrategyFactory implements MailStrategyFactory {
    @Override
    public MailStrategy createStrategy() {
        return new JavaMailStrategy();
    }
}
