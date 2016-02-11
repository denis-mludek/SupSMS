/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.entity;

import javax.persistence.*;
import java.util.Comparator;

/**
 * @author EPTR
 */
@Entity
@Table(name = "sms")
public class Sms implements Comparator<Sms>, Comparable<Sms> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "conversation_fk")
    private Conversation conversation;
    private String message;
    private String phoneNumberSender;
    private Long sendTimestamp;

    public Sms() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSendTimestamp() {
        return sendTimestamp;
    }

    public void setSendTimestamp(Long sendTimestamp) {
        this.sendTimestamp = sendTimestamp;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
        if (conversation.getListSms() == null) {
            conversation.setListSms(null);
        }
    }

    @Override
    public String toString() {
        return "Sms{" + "id=" + id + ", message=" + message + ", sendTimestamp=" + sendTimestamp + '}';
    }

    /**
     * @return the phoneNumberSender
     */
    public String getPhoneNumberSender() {
        return phoneNumberSender;
    }

    /**
     * @param phoneNumberSender the phoneNumberSender to set
     */
    public void setPhoneNumberSender(String phoneNumberSender) {
        this.phoneNumberSender = phoneNumberSender;
    }

    @Override
    public int compare(Sms o1, Sms o2) {
        return o1.getSendTimestamp().compareTo(o2.getSendTimestamp());
    }

    @Override
    public int compareTo(Sms o) {
        return this.getSendTimestamp().compareTo(o.getSendTimestamp());
    }
}
