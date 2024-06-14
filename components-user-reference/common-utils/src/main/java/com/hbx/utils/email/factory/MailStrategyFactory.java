package com.hbx.utils.email.factory;

import com.hbx.utils.email.strategy.MailStrategy;

/**
 * 邮件服务策略工场类
 */
public interface MailStrategyFactory {
    MailStrategy createStrategy();
}
