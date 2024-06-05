package com.hbx.utils.vercode.factory;

import com.hbx.utils.vercode.strategy.AwtStrategy;
import com.hbx.utils.vercode.strategy.VerCodeStrategy;

public class AwtStrategyFactory implements VerCodeStrategyFactory{
    @Override
    public VerCodeStrategy createStrategy() {
        return new AwtStrategy();
    }
}
