package com.hbx.study.spring.profiles;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyProfileBeanConfig {

    @Bean
    @Profile("dev")
    public MyProfileBean myProfileBeanDev() {
        return new MyProfileBean("dev");
    }

    @Bean
    @Profile("prod")
    public MyProfileBean myProfileBeanProd() {
        return new MyProfileBean("prod");
    }
}
