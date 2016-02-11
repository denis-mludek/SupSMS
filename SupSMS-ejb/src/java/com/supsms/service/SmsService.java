/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.service;

import com.supsms.dao.SmsDao;
import com.supsms.entity.Conversation;
import com.supsms.entity.Sms;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.*;
import java.util.List;

/**
 * @author lucas
 */
@Stateless
public class SmsService {
    public static final String ATT_SESSION = "user";
    MessageConsumer messageConsumer;
    TextMessage textMessage;
    @Resource(mappedName = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/myQueue")
    private Queue queue;
    @EJB
    private SmsDao smsDao;
    @EJB
    private ConversationService conversationService;

    public List<Sms> getAllSms() {
        return smsDao.getAllSms();
    }

    public void removeSms(String id) {
        smsDao.removeSms(smsDao.findSmsById(id));
    }

    public Conversation sendMessage(String message, String phoneNumberRecipient, String phoneNumberSender) {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            textMessage = session.createTextMessage();
            //textMessage.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
            textMessage.setText(phoneNumberSender + "\n\r" + message + "\n\r" + phoneNumberRecipient);
            messageProducer.send(textMessage);
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        Sms sms = new Sms();
        sms.setMessage(message);
        sms.setSendTimestamp(System.currentTimeMillis() / 1000L);
        sms.setPhoneNumberSender(phoneNumberSender);
        Conversation conversation = conversationService.getConversationByPhoneNumbers(phoneNumberRecipient, phoneNumberSender);
        sms.setConversation(conversation);
        conversation.addSms(sms);
        smsDao.addSms(sms);
        conversationService.addConversation(conversation);
        return conversation;
    }

    public String receiveMessage() {
        String message = "";
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(queue);
            connection.start();
            textMessage = (TextMessage) messageConsumer.receive();
            message = textMessage.getText();
            session.close();
            connection.close();
            return message;
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return "";
    }
}
