/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao;

import com.supsms.entity.Sms;

import javax.ejb.Local;
import java.util.List;

/**
 * @author YemYem
 */
@Local
public interface SmsDao {
    public Sms addSms(Sms Sms);

    public List<Sms> getAllSms();

    public Sms findSmsById(String SmsId);

    public void removeSms(Sms Sms);
}
