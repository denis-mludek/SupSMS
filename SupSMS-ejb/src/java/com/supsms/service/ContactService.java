/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.service;

import com.supsms.dao.ContactDao;
import com.supsms.entity.Contact;
import com.supsms.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author EPTR
 */
@Stateless
public class ContactService {
    @EJB
    private ContactDao contactDao;

    public void addContact(Contact contact) {
        contactDao.addContact(contact);
    }

    public Contact getContactById(String contactId) {
        return contactDao.findContactById(contactId);
    }

    public Contact getContactByPhone(String phone, User user) {
        return contactDao.findContactByPhoneAndUser(phone, user);
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }

    public List<Contact> getAllContactsFromUser(User user) {
        return contactDao.getAllContactsFromUser(user);
    }

    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }

    public void removeContact(String id) {
        contactDao.removeContact(contactDao.findContactById(id));
    }
}
