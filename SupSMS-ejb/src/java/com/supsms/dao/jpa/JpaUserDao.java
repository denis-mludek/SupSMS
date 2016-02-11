/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao.jpa;

import com.supsms.dao.UserDao;
import com.supsms.entity.Contact;
import com.supsms.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author YemYem
 */
@Stateless
public class JpaUserDao implements UserDao {
    private static final String JPQL_SELECT_BY_PHONE = "SELECT u FROM User u WHERE u.phoneNumber=:phone";
    private static final String PARAM_PHONE = "phone";
    @PersistenceContext
    private EntityManager em;
    private JpaContactDao contactDao;

    @Override
    public User addUser(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        return em.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User findUserById(String userId) {
        Query req = em.createQuery(JPQL_SELECT_BY_PHONE);
        req.setParameter(PARAM_PHONE, userId);
        User user = null;
        try {
            user = (User) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Override
    public void removeUser(User user) {
        for (Contact c : user.getListContacts()) {
            contactDao.removeContact(c);
        }
        em.remove(user);
    }
}
