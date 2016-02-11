/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.webservice;

import com.supsms.entity.Contact;
import com.supsms.entity.User;
import com.supsms.service.ContactService;
import com.supsms.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * @author EPTR
 */
@WebService(serviceName = "ContactWebService")
@Stateless
public class ContactWebService {
    @EJB
    private ContactService contactService;
    @EJB
    private UserService userService;

    @WebMethod(operationName = "getAllContactsForUser")
    public List<Contact> getAllContactsForUser(@WebParam(name = "userPhoneNumber") String phone) {
        return contactService.getAllContactsFromUser(userService.getUserByPhoneNumber(phone));
    }

    @WebMethod(operationName = "updateListContact")
    @Oneway
    public void updateListContact(@WebParam(name = "listContacts") List<Contact> listContacts, @WebParam(name = "userPhoneNumber") String phone) {
        User user = userService.getUserByPhoneNumber(phone);
        user.setListContacts(listContacts);
        userService.updateUser(user);
    }
}
