/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.service;

import com.supsms.dao.ConversationDao;
import com.supsms.entity.Conversation;
import com.supsms.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author YemYem
 */
@Stateless
public class ConversationService {
    @EJB
    private ConversationDao conversationDao;

    public void addConversation(Conversation conversation) {
        conversationDao.addConversation(conversation);
    }

    public Conversation getConversationById(String conversationId) {
        return conversationDao.findConversationById(conversationId);
    }

    public Conversation getConversationByPhoneNumbers(String phone1, String phone2) {
        return conversationDao.getConversationByPhoneNumbers(phone1, phone2);
    }

    public List<Conversation> getAllConversation() {
        return conversationDao.getAllConversation();
    }

    public List<Conversation> getConversationByUser(User user) {
        return conversationDao.getConversationByUser(user);
    }

    public void removeConversation(String id) {
        Conversation conv = conversationDao.findConversationById(id);
        conversationDao.removeConversation(conv);
    }

    public Conversation getConversationByUser(List<User> users) {
        return conversationDao.getConversationByUser(users);
    }
}
