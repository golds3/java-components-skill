package com.hbx.learn;

import com.hbx.learn.apis.CreateApi;
import com.hbx.learn.collection.Person;
import com.hbx.learn.collection.User;
import com.hbx.learn.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CreateApi createApi;

    @Test
    public void testSaveUser(){
        User user =new User(3l,"zhangtiegang","gougang");
        createApi.insertOne(new Person("1","ham",20));
        userRepository.saveUser(user);
    }

    @Test
    public void findUserByUserName(){
        User user= userRepository.findUserByUserName("zhangtiegang");
        System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        User user =new User(2l,"wangchunhua","chuanhua");
        userRepository.updateUser(user);
    }

    @Test
    public void testDeleteUserById(){
        userRepository.deleteUserById(2l);
    }


}