/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.Invoice;
import com.supsms.entity.User;
import com.supsms.service.InvoiceService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author EPTR
 */
@WebServlet(name = "MyInvoicesServlet", urlPatterns = {"/user/myInvoices"})
public class MyInvoicesServlet extends HttpServlet {
    public static final String VIEW_LIST = "/WEB-INF/views/myInvoices.jsp";
    private static final String ATT_SESSION = "user";
    private static final String ATT_LISTINVOICES = "invoices";
    @EJB
    private InvoiceService invoiceService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User userLogged = (User) request.getSession().getAttribute(ATT_SESSION);
        List<Invoice> list = invoiceService.findInvoicesForUser(userLogged);
        request.setAttribute(ATT_LISTINVOICES, list);
        request.getRequestDispatcher(VIEW_LIST).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
