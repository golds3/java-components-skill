package com.hbx.study.spring.profiles;

import org.springframework.context.annotation.Configuration;

public class MyProfileBean {
    private String msg;

    public MyProfileBean(){
    }

    public MyProfileBean(String msg){
        this.msg = msg;
    }

    public void say() {
        System.out.println( msg);
    }
}
