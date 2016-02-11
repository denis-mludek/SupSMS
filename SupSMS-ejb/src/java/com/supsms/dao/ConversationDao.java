/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao;

import com.supsms.entity.Conversation;
import com.supsms.entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author YemYem
 */
@Local
public interface ConversationDao {
    public Conversation addConversation(Conversation conversation);

    public List<Conversation> getAllConversation();

    public Conversation findConversationById(String ConversationId);

    public void removeConversation(Conversation Conversation);

    public Conversation getConversationByUser(List<User> users);

    public List<Conversation> getConversationByUser(User user);

    public Conversation getConversationByPhoneNumbers(String phone1, String phone2);
}
