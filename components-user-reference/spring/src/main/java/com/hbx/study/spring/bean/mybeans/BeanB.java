package com.hbx.study.spring.bean.mybeans;

import org.springframework.stereotype.Component;

@Component
public class BeanB {
    public BeanB() {
        System.out.println("BeanB init");
    }
}
