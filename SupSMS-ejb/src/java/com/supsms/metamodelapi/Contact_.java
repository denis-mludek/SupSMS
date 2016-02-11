/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.metamodelapi;

import com.supsms.entity.Contact;
import com.supsms.entity.User;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author EPTR
 */
@StaticMetamodel(Contact.class)
public class Contact_ {
    public static volatile SingularAttribute<Contact, Long> id;
    public static volatile SingularAttribute<Contact, String> lastName;
    public static volatile SingularAttribute<Contact, String> firstName;
    public static volatile SingularAttribute<Contact, Integer> phoneNumber;
    public static volatile SingularAttribute<Contact, User> user;
}
