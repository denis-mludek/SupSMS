/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.entity;

import com.supsms.service.ContactService;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author EPTR
 */
@Entity
@Table(name = "conversations")
public class Conversation implements Comparator<Conversation>, Comparable<Conversation>, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name = "phoneNumber_string")
    private List<String> phoneNumber;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "conversation", cascade = CascadeType.REMOVE)
    private List<Sms> listSms;

    public Conversation() {
    }

    public String getContactRecipient(User Sender, ContactService contactService) {
        String phoneNumberRecipent = "";
        //on recupere le numero de telephone du Recipient
        for (String phone : phoneNumber)
            if (!phone.equals(Sender.getPhoneNumber()))
                phoneNumberRecipent = phone;
        Contact contact = contactService.getContactByPhone(phoneNumberRecipent, Sender);
        if (contact == null)
            return phoneNumberRecipent;
        else return contact.getFirstName() + " " + contact.getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Sms> getListSms() {
        return listSms;
    }

    public void setListSms(List<Sms> listSms) {
        this.listSms = listSms;
    }

    public void addSms(Sms sms) {
        if (this.listSms == null) {
            this.listSms = new Stack<Sms>();
        }
        this.listSms.add(sms);
    }

    @Override
    public String toString() {
        return "Conversation{" + "id=" + getId() + ", listSms=" + getListSms() + '}';
    }

    /**
     * @return the phoneNumber
     */
    public List<String> getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(List<String> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addPhoneNumber(String phoneNumber) {
        if (this.phoneNumber == null) {
            this.phoneNumber = new Stack<String>();
        }
        this.phoneNumber.add(phoneNumber);
    }

    @Override
    public int compare(Conversation o1, Conversation o2) {
        List<Sms> smso1 = o1.getListSms();
        List<Sms> smso2 = o2.getListSms();
        Collections.sort(smso1);
        Collections.sort(smso2);
        return smso1.get(smso1.size() - 1).compareTo(smso2.get(smso2.size() - 1));
    }

    @Override
    public int compareTo(Conversation o) {
        List<Sms> smso1 = this.getListSms();
        List<Sms> smso2 = o.getListSms();
        Collections.sort(smso1);
        Collections.sort(smso2);
        return smso1.get(smso1.size() - 1).compareTo(smso2.get(smso2.size() - 1));
    }
}
