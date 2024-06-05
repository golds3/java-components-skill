package com.hbx.utils.vercode.factory;

import com.hbx.utils.vercode.strategy.VerCodeStrategy;

/**
 * 验证码生成策略工场类
 */
public interface VerCodeStrategyFactory {
    VerCodeStrategy createStrategy();
}
