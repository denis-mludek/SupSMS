/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao.jpa;

import com.supsms.dao.ConversationDao;
import com.supsms.entity.Conversation;
import com.supsms.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author YemYem
 */
@Stateless
public class JpaConversationDao implements ConversationDao {
    private static final String JPQL_SELECT_BY_PHONE = "SELECT u FROM Conversation u WHERE u.id=:conversation";
    private static final String PARAM_PHONE = "conversation";
    @PersistenceContext
    private EntityManager em;
    private JpaSmsDao smsDao;

    @Override
    public Conversation addConversation(Conversation conversation) {
        em.persist(conversation);
        System.out.println("Une conversation a été ajouter");
        return conversation;
    }

    @Override
    public List<Conversation> getConversationByUser(User user) {
        if (user == null) return null;
        return em.createQuery("SELECT c FROM Conversation c where c.phoneNumber in ('" + user.getPhoneNumber() + "')").getResultList();
    }

    @Override
    public Conversation getConversationByPhoneNumbers(String phone1, String phone2) {
        if (phone1.equals(phone2)) {
            throw new IllegalArgumentException("PhoneNumber1 and PhoneNumber2 are egals");
        }
        try {
            return (Conversation) em.createQuery("SELECT c FROM Conversation c where c.phoneNumber in ('" + phone1 + "') and c.phoneNumber in ('" + phone2 + "')").getSingleResult();
        } catch (NoResultException e) {
            Conversation conversation = new Conversation();
            conversation.addPhoneNumber(phone1);
            conversation.addPhoneNumber(phone2);
            return conversation;
        }
    }

    @Override
    public List<Conversation> getAllConversation() {
        return em.createQuery("SELECT u FROM Conversation u").getResultList();
    }

    @Override
    public Conversation findConversationById(String conversationId) {
        return em.find(Conversation.class, Long.valueOf(conversationId));
    }

    @Override
    public void removeConversation(Conversation conversation) {
        em.remove(conversation);
    }

    @Override
    public Conversation getConversationByUser(List<User> users) {
        return null;
    }
}
