package com.supsms.dao.jpa;

import com.supsms.dao.OfferDao;
import com.supsms.entity.Offer;

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
public class JpaOfferDao implements OfferDao {
    private static final String JPQL_SELECT_BY_ID = "SELECT o FROM Offer o WHERE o.id=:id";
    private static final String JPQL_SELECT_BY_NAME = "SELECT o FROM Offer o WHERE o.name=:name";
    private static final String PARAM_ID = "id";
    private static final String PARAM_NAME = "name";
    @PersistenceContext
    private EntityManager em;

    @Override
    public Offer addOffer(Offer offer) {
        em.persist(offer);
        return offer;
    }

    @Override
    public List<Offer> getAllOffers() {
        return em.createQuery("SELECT u FROM Offer u").getResultList();
    }

    @Override
    public Offer findOfferById(String OfferId) {
        Query req = em.createQuery(JPQL_SELECT_BY_ID);
        req.setParameter(PARAM_ID, OfferId);
        Offer offer = null;
        try {
            offer = (Offer) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return offer;
    }

    @Override
    public void removeOffer(Offer offer) {
        em.remove(offer);
    }

    @Override
    public void updateOffer(Offer offer) {
        em.merge(offer);
    }

    @Override
    public Offer findOfferByName(String name) {
        Query req = em.createQuery(JPQL_SELECT_BY_NAME);
        req.setParameter(PARAM_NAME, name);
        Offer offer = null;
        try {
            offer = (Offer) req.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return offer;
    }
}
