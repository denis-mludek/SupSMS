package com.supsms.dao;

import com.supsms.entity.Offer;

import javax.ejb.Local;
import java.util.List;

/**
 * @author EPTR
 */
@Local
public interface OfferDao {
    public Offer addOffer(Offer offer);

    public List<Offer> getAllOffers();

    public Offer findOfferById(String offerId);

    public Offer findOfferByName(String name);

    public void updateOffer(Offer offer);

    public void removeOffer(Offer offer);
}
