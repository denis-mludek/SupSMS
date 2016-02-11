package com.supsms.dao.jpa;

import com.supsms.dao.InvoiceDao;
import com.supsms.entity.Invoice;
import com.supsms.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author EPTR
 */
@Stateless
public class JpaInvoiceDao implements InvoiceDao {
    private static final String JPQL_SELECT_BY_ID = "SELECT i FROM Invoice i WHERE i.id=:id";
    private static final String JPQL_SELECT_BY_USER = "SELECT i FROM Invoice i WHERE i.user=:user";
    private static final String PARAM_ID = "id";
    private static final String PARAM_USER = "user";
    @PersistenceContext
    private EntityManager em;

    @Override
    public Invoice addInvoice(Invoice invoice) {
        em.persist(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return em.createQuery("SELECT i FROM Invoice i").getResultList();
    }

    @Override
    public Invoice findInvoiceById(String InvoiceId) {
        Query req = em.createQuery(JPQL_SELECT_BY_ID);
        req.setParameter(PARAM_ID, InvoiceId);
        Invoice invoice = null;
        try {
            invoice = (Invoice) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return invoice;
    }

    @Override
    public void removeInvoice(Invoice invoice) {
        em.remove(invoice);
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        em.merge(invoice);
    }

    @Override
    public List<Invoice> findInvoicesForUser(User user) {
        Query req = em.createQuery(JPQL_SELECT_BY_USER);
        req.setParameter(PARAM_USER, user);
        List<Invoice> invoices = null;
        try {
            invoices = req.getResultList();
        } catch (NoResultException e) {
            return null;
        }
        return invoices;
    }
}
