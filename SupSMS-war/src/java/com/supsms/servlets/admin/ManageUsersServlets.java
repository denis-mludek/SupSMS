/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets.admin;

import com.supsms.entity.User;
import com.supsms.service.PasswordService;
import com.supsms.service.UserService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "ManageUsersServlets", urlPatterns = {"/admin/users"})
public class ManageUsersServlets extends HttpServlet {
    public static final String VIEW_LISTUSERS = "/WEB-INF/views/admin/listUsers.jsp";
    public static final String VIEW_FORMUSER = "/WEB-INF/views/admin/formUser.jsp";
    public static final String ATT_SESSION = "user";
    public static final String ATT_LISTUSERS = "listUsers";
    @EJB
    private UserService userService;
    @EJB
    private PasswordService passwordService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList();
        String action = request.getParameter("action");
        String userId = request.getParameter("id");
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_LISTUSERS);
        boolean idUserOk = false;
        if (userId != null && !userId.isEmpty()) {
            idUserOk = true;
        }
        // If param action (add, edit, delete)
        if (action != null && !action.isEmpty() && (action.equals("add") || action.equals("delete"))) {
            if (action.equals("add")) {
                dispatcher = request.getRequestDispatcher(VIEW_FORMUSER);
            }
            if (action.equals("delete")) {
                if (idUserOk) {
                    // remove the user.
                    try {
                        userService.removeUser(userId);
                    } catch (Exception e) {
                        errors.add(e.getMessage());
                    }
                    if (errors.isEmpty()) {
                        response.sendRedirect(request.getHeader("referer"));
                        return;
                    }
                }
            }
        }
        // No param action -> just display list users 
        else {
            List<User> listUsers = userService.getAllUser();
            request.setAttribute(ATT_LISTUSERS, listUsers);
        }
        request.setAttribute("errors", errors);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList();
        // Data of the form
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String creditCard = request.getParameter("creditCard");
        String Expiration = request.getParameter("Expiration");
        String Cryptogramme = request.getParameter("Cryptogramme");
        String password = request.getParameter("password");
        String admin = request.getParameter("checkAdmin");
        // Send them back in case of errors
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("email", email);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("creditCard", creditCard);
        request.setAttribute("Expiration", Expiration);
        request.setAttribute("Cryptogramme", Cryptogramme);
        request.setAttribute("password", password);
        request.setAttribute("admin", admin);
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
            request.getRequestDispatcher(VIEW_FORMUSER).forward(request, response);
            return;
        }
        // User exists ?
        if (userService.getUserByPhoneNumber(phoneNumber) != null) {
            errors.add("This phone number is already taken.");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher(VIEW_FORMUSER).forward(request, response);
            return;
        }
        // No error so add user in DB.
        if (admin.equals("1")) {
            user.setAdminBool(true);
        }
        userService.addUser(user);
        // Finished !
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
