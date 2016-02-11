/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.service;

import com.supsms.dao.OfferDao;
import com.supsms.entity.Offer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * @author EPTR
 */
@Stateless
public class OfferService {
    @EJB
    private OfferDao offerDao;

    public Offer addOffer(Offer offer) {
        return offerDao.addOffer(offer);
    }

    public List<Offer> getAllOffers() {
        return offerDao.getAllOffers();
    }

    public Offer findOfferById(String offerId) {
        return offerDao.findOfferById(offerId);
    }

    public Offer findOfferByName(String name) {
        return offerDao.findOfferByName(name);
    }

    public void updateOffer(Offer offer) {
        offerDao.updateOffer(offer);
    }

    public void removeOffer(Offer offer) {
        offerDao.removeOffer(offer);
    }
}
