package com.hbx.study.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableApolloConfig
public class ApolloSpringboot2Application {

    public static void main(String[] args) {
        SpringApplication.run(ApolloSpringboot2Application.class, args);
    }

}
