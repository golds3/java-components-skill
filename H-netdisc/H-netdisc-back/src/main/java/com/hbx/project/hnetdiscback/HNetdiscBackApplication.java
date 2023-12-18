package com.hbx.project.hnetdiscback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
@EnableScheduling
public class HNetdiscBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(HNetdiscBackApplication.class, args);
    }

}
