package com.supsms.dao;

import com.supsms.entity.Invoice;
import com.supsms.entity.User;

import javax.ejb.Local;
import java.util.List;

/**
 * @author EPTR
 */
@Local
public interface InvoiceDao {
    public Invoice addInvoice(Invoice invoice);

    public List<Invoice> getAllInvoices();

    public Invoice findInvoiceById(String invoiceId);

    public List<Invoice> findInvoicesForUser(User user);

    public void updateInvoice(Invoice invoice);

    public void removeInvoice(Invoice invoice);
}
