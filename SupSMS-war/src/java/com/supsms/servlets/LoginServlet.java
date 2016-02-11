/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.User;
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
 * @author lucas
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    public static final String VIEW_LOGIN = "/WEB-INF/views/login.jsp";
    public static final String VIEW_INDEX = "/WEB-INF/views/index.jsp";
    public static final String ATT_SESSION = "user";
    @EJB
    private UserService userService;
    @EJB
    private PasswordService passwordService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(VIEW_LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        List<String> errors = new ArrayList();
        User user = userService.getUserByPhoneNumber(phoneNumber);
        //On regarde si L'utilisateur existe bien
        if (user == null) {
            errors.add("Sorry, no account uses this phone number.");
            request.setAttribute("errors", errors);
            request.setAttribute("phoneNumberCo", phoneNumber);
            request.getSession().setAttribute(ATT_SESSION, null);
            request.getRequestDispatcher(VIEW_LOGIN).forward(request, response);
            return;
        }
        try {
            password = passwordService.encrypt(password);
        } catch (Exception ex) {
            errors.add("An error occured while encrypting.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(VIEW_LOGIN).forward(request, response);
            return;
        }
        // On regarde si le mot de passe est le bon
        if (user.getPassword().equals(password)) {
            request.getSession().setAttribute(ATT_SESSION, user);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            errors.add("Password is incorrect.");
            request.setAttribute("errors", errors);
            request.setAttribute("phoneNumberCo", phoneNumber);
            request.getSession().setAttribute(ATT_SESSION, null);
            request.getRequestDispatcher(VIEW_LOGIN).forward(request, response);
            return;
        }
    }
}
