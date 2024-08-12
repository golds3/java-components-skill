package com.hbx.utils.log.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDemo {
    private static final Logger log = LoggerFactory.getLogger(TestDemo.class);
    public static void main(String[] args) {
        String msg = "print log, current level: {}";
        log.trace(msg, "trace");
        log.debug(msg, "debug");
        log.info(msg, "info");
        log.warn(msg, "warn");
        log.error(msg, "error");
    }
}
