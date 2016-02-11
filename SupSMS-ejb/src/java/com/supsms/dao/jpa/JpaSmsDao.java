/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao.jpa;

import com.supsms.dao.SmsDao;
import com.supsms.entity.Sms;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author YemYem
 */
@Stateless
public class JpaSmsDao implements SmsDao {
    private static final String JPQL_SELECT_BY_ID = "SELECT s FROM Sms s WHERE s.id=:id";
    private static final String PARAM_ID = "id";
    @PersistenceContext
    private EntityManager em;

    @Override
    public Sms addSms(Sms sms) {
        em.persist(sms);
        System.out.println("Un Sms a été ajouter");
        return sms;
    }

    @Override
    public List<Sms> getAllSms() {
        return em.createQuery("SELECT u FROM Sms u").getResultList();
    }

    @Override
    public Sms findSmsById(String SmsId) {
        Query req = em.createQuery(JPQL_SELECT_BY_ID);
        req.setParameter(PARAM_ID, SmsId);
        Sms sms = null;
        try {
            sms = (Sms) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return sms;
    }

    @Override
    public void removeSms(Sms sms) {
        em.remove(sms);
    }
}
