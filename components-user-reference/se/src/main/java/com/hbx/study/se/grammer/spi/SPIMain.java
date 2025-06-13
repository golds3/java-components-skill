package com.hbx.study.se.grammer.spi;

import java.util.ServiceLoader;

public class SPIMain {

    public static void main(String[] args) {
        // 去找META-INF/services/com.hbx.study.se.grammer.spi.IShout，
        // 加载IShout的实现接口(SPI),对shout方法进行扩展
        //Dog,Cat一般都不是本工程的，一般是引入jar包带来的，基于SPI机制进行扩展使用
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout shout : shouts) {
            shout.shout();
        }

    }
}
