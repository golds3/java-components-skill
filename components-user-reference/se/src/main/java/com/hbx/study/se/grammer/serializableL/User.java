package com.hbx.study.se.grammer.serializableL;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class User implements Serializable {
//    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private transient String address;//transient关键字修饰的字段不会被序列化


    public static void main(String[] args) {
        System.out.println(System.getProperty("java.class.path"));
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
    }

}
