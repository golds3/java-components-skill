package com.hbx.learn;

import com.hbx.learn.collection.User;
import com.hbx.learn.dao.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser(){
        User user =new User(2l,"zhangtiegang","gougang");
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