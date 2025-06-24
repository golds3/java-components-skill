package com.hbx.study.spring;

import com.hbx.study.spring.event.UserService;
import com.hbx.study.spring.event.events.RegisterSuccessEvent;
import com.hbx.study.spring.profiles.MyProfileBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

/**
 * aot 编译
 * 引入maven org.graalvm.buildtools插件
 *
 mvn native:compile



 ./target/sapplication


 * */
@SpringBootApplication
@EnableAsync
public class SApplication implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
    private static UserService userService;
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(SApplication.class, args);
        applicationContext.publishEvent(new RegisterSuccessEvent("👏👏"));
        try{
            userService.trans();
        }catch (Exception e){
            e.printStackTrace();
        }

        MyProfileBean bean = applicationContext.getBean(MyProfileBean.class);
        bean.say();
//        TimeUnit.MILLISECONDS.sleep(10000);
//        ((AbstractApplicationContext)applicationContext).close();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.userService = applicationContext.getBean(UserService.class);
    }
}
