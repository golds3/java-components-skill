package com.hbx.utils.log.configuration;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class SensitiveConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        //自定义转换器，进行日志脱敏
        String preLogs = super.convert(event);
        String afterLogs = dealLogs(preLogs);
        return afterLogs;
    }


    /**
     * 脱敏逻辑
     */
    public String dealLogs(String preLogs){
        if (preLogs.length()==2){
            return "脱敏";
        }
        return preLogs;
    }
}
