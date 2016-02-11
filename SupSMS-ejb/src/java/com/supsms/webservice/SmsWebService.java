/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.webservice;

import com.supsms.entity.Conversation;
import com.supsms.entity.Sms;
import com.supsms.service.ConversationService;
import com.supsms.service.SmsService;
import com.supsms.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EPTR
 */
@WebService(serviceName = "SmsWebService")
@Stateless
public class SmsWebService {
    @EJB
    private SmsService smsService;
    @EJB
    private UserService userService;
    @EJB
    private ConversationService conversationService;

    // App -> Client
    @WebMethod(operationName = "getAllSmsForUser")
    public List<Sms> getAllSmsForUser(@WebParam(name = "userPhoneNumber") String phone) {
        List<Conversation> listConv = conversationService.getConversationByUser(userService.getUserByPhoneNumber(phone));
        List<Sms> listSms = new ArrayList();
        for (Conversation c : listConv) {
            for (Sms s : c.getListSms()) {
                listSms.add(s);
            }
        }
        return listSms;
    }
    // Client -> App
    /* @WebMethod(operationName = "updateListSms")
    @Oneway
    public void updateListSms(@WebParam(name = "listSms")List<Sms> listSms, @WebParam(name="userPhoneNumber")String phoneUser) {
        User user = userService.getUserByPhoneNumber(phoneUser);
        List<String> phones;
        
        for(Sms s : listSms){
            phones = s.getConversation().getPhoneNumber();
            conversationService.getConversationByPhoneNumbers(phones.get(0), phones.get(1));
                
                
            }
        }
        user.setListContacts(listContacts);
        userService.updateUser(user);
    } */
}
