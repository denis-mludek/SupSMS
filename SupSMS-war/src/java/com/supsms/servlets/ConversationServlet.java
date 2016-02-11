/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.Conversation;
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

/**
 * @author lucas
 */
@WebServlet(name = "ConversationServlet", urlPatterns = {"/user/conversations"})
public class ConversationServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/conversation.jsp";
    @EJB
    private ConversationService conversationService;
    @EJB
    private ContactService contactService;
    @EJB
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("contactService", contactService);
        request.setAttribute("userService", userService);
        //TODO verifier si on a droit de lire cette conf
        //On recupere la conversation
        String numeroConversation = request.getParameter("num");
        Conversation conversation = conversationService.getConversationById(numeroConversation);
        request.setAttribute("conversationSMS", conversation.getListSms());
        request.setAttribute("conversation", conversation);
        request.setAttribute("numConversation", numeroConversation);
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
