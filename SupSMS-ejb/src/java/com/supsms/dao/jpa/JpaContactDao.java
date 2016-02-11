/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.dao.jpa;

import com.supsms.dao.ContactDao;
import com.supsms.entity.Contact;
import com.supsms.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.List;

/**
 * @author EPTR
 */
@Stateless
public class JpaContactDao implements ContactDao {
    private static final String JPQL_SELECT_BY_USER = "SELECT u FROM Contact u WHERE u.user=:user ORDER BY u.lastName ASC";
    private static final String PARAM_USER = "user";
    private static final String JPQL_SELECT_BY_PHONE_AND_USER = "SELECT u FROM Contact u WHERE u.phoneNumber=:phone and  u.user=:user ";
    private static final String PARAM_PHONE = "phone";
    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder criteriaBuilder;
    private Date date = new Date();

    @Override
    public Contact addContact(Contact contact) {
        contact.getUser().setLastUpdateContacts();
        em.merge(contact.getUser());
        em.persist(contact);
        return contact;
    }

    @Override
    public List<Contact> getAllContacts() {
        return em.createQuery("SELECT c FROM Contact c").getResultList();
    }

    @Override
    public Contact findContactById(String contactId) {
        return em.find(Contact.class, Long.valueOf(contactId));
    }

    @Override
    public void removeContact(Contact contact) {
        // Update the timestamp for syncing webservice
        contact.getUser().setLastUpdateContacts();
        em.merge(contact.getUser());
        em.remove(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contact.getUser().setLastUpdateContacts();
        em.merge(contact.getUser());
        em.merge(contact);
    }

    @Override
    public List<Contact> getAllContactsFromUser(User user) {
        
        /* CriteriaQuery<Contact> criteriaQuery = 	criteriaBuilder.createQuery(Contact.class);
        Root<Contact> contact = criteriaQuery.from(Contact.class);
        criteriaQuery.where( 
                criteriaBuilder.equal( contact.get(Contact_.user.get), user.getPhoneNumber()
        ));
        List<Contact> results = em.createQuery(criteriaQuery).getResultList();
        
        return results; */
        Query req = em.createQuery(JPQL_SELECT_BY_USER);
        req.setParameter(PARAM_USER, user);
        List<Contact> listContacts = null;
        try {
            listContacts = req.getResultList();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.out.println(e);
        }
        return listContacts;
    }

    @Override
    public Contact findContactByPhoneAndUser(String phone, User user) {
        Query req = em.createQuery(JPQL_SELECT_BY_PHONE_AND_USER);
        req.setParameter(PARAM_PHONE, phone);
        req.setParameter(PARAM_USER, user);
        Contact contact = null;
        try {
            contact = (Contact) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return contact;
    }
}
