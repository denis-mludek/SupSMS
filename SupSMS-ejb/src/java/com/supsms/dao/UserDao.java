/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao;

import com.supsms.entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author YemYem
 */
@Local
public interface UserDao {
    public User addUser(User user);

    public List<User> getAllUser();

    public User findUserById(String userId);

    public void updateUser(User user);

    public void removeUser(User user);
}
