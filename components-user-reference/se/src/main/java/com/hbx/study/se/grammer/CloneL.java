package com.hbx.study.se.grammer;

import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

/**
 * 深拷贝和浅拷贝
 */
public class CloneL {


    /**
     * 浅拷贝
     */
    @SneakyThrows
    public static <T>T shallowClone(T obj, Class<T> clazz){
        T t =clazz.getConstructor().newInstance();
        BeanUtils.copyProperties(obj,t);
        return t;
    }
    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A();
        a.an  = "a";
        B b = new B();
        b.bn = "b";
        a.b = b;
        System.out.println("before clone:"+a);
        A a1 = shallowClone(a,A.class);
        System.out.println("shallowClone get:"+a1);
        assert  a1.b == a.b;
        //1.可以重写clone方法，使用Object.clone()进行深拷贝（如果不重写的话默认是浅拷贝）
        A deepClone = (A) a.clone();
        System.out.println("deepClone get:"+deepClone);
        assert deepClone.b != a.b;

        //2.使用序列话的方式实现深拷贝
        A clone = SerializationUtils.clone(a);
        assert clone.b != a.b;


    }
}

@ToString
@NoArgsConstructor
@Getter
@Setter
class A implements Cloneable, Serializable {
    String an;
    B b;

    @Override
    public Object clone() throws CloneNotSupportedException {
        A clone = (A) super.clone();
        clone.b = (B) clone.b.clone();//  深拷贝
        return clone;
    }
}
@ToString
@NoArgsConstructor
@Getter
@Setter
class B implements Cloneable,  Serializable{
    String bn;
    public B B(){
        return new B();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
