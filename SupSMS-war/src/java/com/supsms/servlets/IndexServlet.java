/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.Conversation;
import com.supsms.entity.User;
import com.supsms.service.ContactService;
import com.supsms.service.ConversationService;
import com.supsms.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author YemYem
 */
@WebServlet(name = "IndexServlet", urlPatterns = {""})
public class IndexServlet extends HttpServlet {
    public static final String VIEW_INDEX = "/WEB-INF/views/index.jsp";
    @EJB
    private UserService userService;
    @EJB
    private ConversationService conversationService;
    @EJB
    private ContactService contactService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //On regarde si il y a pas de conversation a supprimer
        if (request.getParameter("delete") != null) {
            conversationService.removeConversation(request.getParameter("delete"));
        }
        ;
        //On recupere tout les conversations:
        User user;
        user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Conversation> conversations = conversationService.getConversationByUser(user);
            Collections.sort(conversations);
            request.setAttribute("conversations", conversations);
            request.setAttribute("contactService", contactService);
        }
        // Stats for index
        List<Conversation> allConversations = conversationService.getAllConversation();
        int nbSmsTotal = 0;
        if (allConversations != null) {
            for (Conversation c : allConversations) {
                nbSmsTotal += c.getListSms().size();
            }
        }
        request.setAttribute("userCount", userService.getAllUser().size());
        request.setAttribute("smsCount", nbSmsTotal);
        request.getRequestDispatcher(VIEW_INDEX).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO
    }
}
