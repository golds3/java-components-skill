package com.hbx.study.apollo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApolloSpringboot2ApplicationTests {
    @Value("${timeout:1}")
    private String timeout;

    @Test
    void contextLoads() {
        System.out.println(timeout);

    }

}
