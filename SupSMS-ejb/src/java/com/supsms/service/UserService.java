/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.service;

import com.supsms.dao.UserDao;
import com.supsms.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author YemYem
 */
@Stateless
public class UserService {
    @EJB
    private UserDao userDao;

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUserByPhoneNumber(String userId) {
        return userDao.findUserById(userId);
    }

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void removeUser(String id) {
        userDao.removeUser(userDao.findUserById(id));
    }
}
