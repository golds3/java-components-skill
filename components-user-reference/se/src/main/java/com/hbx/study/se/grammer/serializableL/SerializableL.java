package com.hbx.study.se.grammer.serializableL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * 序列化：把对象的状态信息（如 Java 对象）转换成可以存储或传输的形式的过程。 （对于原生APi就是把一个Java对象变成字节数组，对于fastjson这些就是变成json字符串）
 * 反序列化：把数据恢复成Java对象
 * serialVersionUID 版本号：如果不主动生命，JVM会自动生成一个版本号(根据当前类的结构)
 * 如果改变类的结构，那么JVM会生成一个新的版本号，那么原来已经序列化的数据就无法反序列化
 * 所以最好自己生命一个版本号，这样JVM就不会生成新的版本号了，保证数据兼容
 *
 * 如果使用Java原生的API(ObjectOutputStream)进行序列化，那么对象必须实现Serializable接口
 * 但是像Jackson、Gson这样的第三方库，不需要实现Serializable接口
 */
public class SerializableL {

    public static void main(String[] args) {
        User user = new User();
        user.setName("hbx");
        user.setAge(18);
        user.setAddress("beijing");
        System.out.println("before serial:"+user);

        System.out.println("serialing...");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("temp"))){
            oos.writeObject(user);
        }catch (Exception e){

        }

        System.out.println("deserial...");
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("temp"))){
            User user1 = (User)ois.readObject();
            System.out.println("after serial:"+user1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
