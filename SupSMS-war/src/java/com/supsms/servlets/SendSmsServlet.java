/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.Conversation;
import com.supsms.entity.User;
import com.supsms.service.SmsService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.supsms.filters.UserFilter.ATT_SESSION;

/**
 * @author lucas
 */
@WebServlet(name = "SendSmsServlet", urlPatterns = {"/user/sendSms"})
public class SendSmsServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/views/sendSms.jsp";
    @EJB
    private SmsService smsService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //on recupere le numero de telephone a qui envoyer
        String phone = request.getParameter("phone");
        request.setAttribute("phone", phone);
        request.getRequestDispatcher(VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String msg = request.getParameter("msg");
        User user = null;
        user = (User) request.getSession(true).getAttribute(ATT_SESSION);
        Conversation conversaton = smsService.sendMessage(msg, phoneNumber, user.getPhoneNumber());
        response.sendRedirect(request.getContextPath() + "/user/conversations?num=" + conversaton.getId());
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
