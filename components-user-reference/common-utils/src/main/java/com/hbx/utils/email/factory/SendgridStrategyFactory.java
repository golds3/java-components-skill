package com.hbx.utils.email.factory;

import com.hbx.utils.email.strategy.MailStrategy;
import com.hbx.utils.email.strategy.SendgridStrategy;

public class SendgridStrategyFactory implements MailStrategyFactory {
    @Override
    public MailStrategy createStrategy() {
        return new SendgridStrategy();
    }
}
