/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao;

import com.supsms.entity.Contact;
import com.supsms.entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author EPTR egze
 */
@Local
public interface ContactDao {
    Contact addContact(Contact contact);

    public List<Contact> getAllContacts();

    public List<Contact> getAllContactsFromUser(User user);

    public Contact findContactById(String contactId);

    public Contact findContactByPhoneAndUser(String phone, User user);

    public void updateContact(Contact contact);

    public void removeContact(Contact contact);
}
