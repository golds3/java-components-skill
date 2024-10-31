package com.hbx.learn.dao;

import com.hbx.learn.collection.User;

public interface UserRepository {
    public void saveUser(User user);

    public User findUserByUserName(String userName);

    public long updateUser(User user);

    public void deleteUserById(Long id);
}
