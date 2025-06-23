package com.hbx.study.spring;

import com.hbx.study.spring.bean.mybeans.BeanA;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * aot 编译
 * 引入maven org.graalvm.buildtools插件
 *
 mvn native:compile



 ./target/sapplication


 * */
@SpringBootApplication
public class SApplication implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(SApplication.class, args);
        TimeUnit.MILLISECONDS.sleep(100);
        ((AbstractApplicationContext)applicationContext).close();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
