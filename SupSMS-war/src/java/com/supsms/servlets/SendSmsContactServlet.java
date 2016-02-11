/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.Contact;
import com.supsms.entity.User;
import com.supsms.service.ContactService;
import com.supsms.service.ConversationService;
import com.supsms.service.SmsService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author lucas
 */
@WebServlet(name = "SendSmsContactServlet", urlPatterns = {"/user/SendSmsContact"})
public class SendSmsContactServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/SendSmsContactPopup.jsp";
    private static final String ATT_LISTCONTACTS = "listContacts";
    @EJB
    private ConversationService conversationService;
    @EJB
    private ContactService contactService;
    @EJB
    private SmsService smsService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //On recupere tout les conversations:
        User user;
        user = (User) request.getSession().getAttribute("user");
        List<Contact> listContacts = contactService.getAllContactsFromUser(user);
        request.setAttribute(ATT_LISTCONTACTS, listContacts);
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
