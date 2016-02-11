/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.Invoice;
import com.supsms.entity.Offer;
import com.supsms.entity.User;
import com.supsms.service.InvoiceService;
import com.supsms.service.OfferService;
import com.supsms.service.PasswordService;
import com.supsms.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author EPTR
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    private static final String VIEW_REGISTER = "/WEB-INF/views/register.jsp";
    private static final String ATT_SESSION = "user";
    @EJB
    private UserService userService;
    @EJB
    private PasswordService passwordService;
    @EJB
    private OfferService offerService;
    @EJB
    private InvoiceService invoiceService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_REGISTER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList();
        //On recupere les informations du forumulaire
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String creditCard = request.getParameter("creditCard");
        String Expiration = request.getParameter("Expiration");
        String Cryptogramme = request.getParameter("Cryptogramme");
        String password = request.getParameter("password");
        //On stock les attributs en cas d'erreur
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("email", email);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("creditCard", creditCard);
        request.setAttribute("Expiration", Expiration);
        request.setAttribute("Cryptogramme", Cryptogramme);
        request.setAttribute("password", password);
        //on creer l'utilisateur a ajouter dans la DB
        User user = new User();
        try {
            user.setPhoneNumber(phoneNumber);
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setCreditCardNumber(creditCard);
            user.setCreditCardDate(Expiration);
            user.setCardCecurityCode(Cryptogramme);
            user.setPassword(passwordService.encrypt(password));
        } catch (Exception e) {
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(VIEW_REGISTER).forward(request, response);
            return;
        }
        //On verifie si le User existe pas deja
        if (userService.getUserByPhoneNumber(phoneNumber) != null) {
            errors.add("This phone Number is already taken");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(VIEW_REGISTER).forward(request, response);
            return;
        }
        // If its the first User to register (supposed to be you), Admin = true , create basic offer in DB
        if (userService.getAllUser().isEmpty()) {
            user.setAdminBool(true);
            Offer basicOffer = new Offer();
            basicOffer.setName("Basic offer");
            basicOffer.setDescription("Join our great community in a simple click and enjoy the amazing service today.<br>\n" +
                    "                    Our offer is clear & simple. A single price for an unlimited access to our network.<br><br>\n" +
                    "                    <span>$10 per month only !</span><br><br>\n" +
                    "                    <span>No message limit !</span><br><br>\n" +
                    "                    <span>No contact limit !</span><br><br>");
            basicOffer.setPayementFrequency("monthly");
            basicOffer.setPrice(10);
            offerService.addOffer(basicOffer);
        }
        // Set Basic Offer for every user, since we have only 1 offer...
        user.setOffer(offerService.findOfferByName("Basic offer"));
        // Create the first invoice and attach it to the user
        Invoice invoice = new Invoice();
        invoice.setAmount(user.getOffer().getPrice());
        invoice.setTimestampDate();
        invoiceService.addInvoice(invoice);
        user.getListInvoice().add(invoice);
        userService.addUser(user);
        invoice.setUser(user);
        invoiceService.updateInvoice(invoice);
        request.setAttribute("errors", errors);
        request.getSession().setAttribute(ATT_SESSION, user);
        response.sendRedirect(request.getContextPath() + "/");
    }
}
