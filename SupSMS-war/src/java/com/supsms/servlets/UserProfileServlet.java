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
 * @author EPTR
 */
@WebServlet(name = "UserProfileServlet", urlPatterns = {"/user/myProfile"})
public class UserProfileServlet extends HttpServlet {
    private static final String ATT_SESSION = "user";
    private static final String VIEW_FORMPROFILE = "/WEB-INF/views/formUserProfile.jsp";
    @EJB
    private UserService userService;
    @EJB
    private PasswordService passwordService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList();
        User userLogged = (User) request.getSession().getAttribute(ATT_SESSION);
        request.setAttribute("email", userLogged.getEmail());
        request.setAttribute("firstName", userLogged.getFirstName());
        request.setAttribute("lastName", userLogged.getLastName());
        request.setAttribute("creditCard", userLogged.getCreditCardNumber());
        request.setAttribute("Expiration", userLogged.getCreditCardDate());
        request.setAttribute("Cryptogramme", userLogged.getCardCecurityCode());
        request.getRequestDispatcher(VIEW_FORMPROFILE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList();
        User userLogged = (User) request.getSession().getAttribute(ATT_SESSION);
        // Data from the form.
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String creditCard = request.getParameter("creditCard");
        String Expiration = request.getParameter("Expiration");
        String Cryptogramme = request.getParameter("Cryptogramme");
        String password = request.getParameter("password");
        // Send them back in case of errors.
        request.setAttribute("email", email);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("creditCard", creditCard);
        request.setAttribute("Expiration", Expiration);
        request.setAttribute("Cryptogramme", Cryptogramme);
        try {
            if (email != null && !email.isEmpty()) {
                userLogged.setEmail(email);
            }
            if (firstName != null && !firstName.isEmpty()) {
                userLogged.setFirstName(firstName);
            }
            if (lastName != null && !lastName.isEmpty()) {
                userLogged.setLastName(lastName);
            }
            if (creditCard != null && !creditCard.isEmpty()) {
                userLogged.setCreditCardNumber(creditCard);
            }
            if (Expiration != null && !Expiration.isEmpty()) {
                userLogged.setCreditCardDate(Expiration);
            }
            if (Cryptogramme != null && !Cryptogramme.isEmpty()) {
                userLogged.setCardCecurityCode(Cryptogramme);
            }
            if (password != null && !password.isEmpty()) {
                userLogged.setPassword(passwordService.encrypt(password));
            }
        } catch (Exception e) {
            errors.add(e.getMessage());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(VIEW_FORMPROFILE).forward(request, response);
            return;
        }
        userService.updateUser(userLogged);
        response.sendRedirect(request.getContextPath() + "/user/myProfile");
    }
}
