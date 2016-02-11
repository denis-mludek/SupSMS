/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.entity.Contact;
import com.supsms.entity.Conversation;
import com.supsms.entity.User;
import com.supsms.service.ContactService;
import com.supsms.service.ConversationService;

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
@WebServlet(name = "ContactServlet", urlPatterns = {"/user/contacts"})
public class ManageContactsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ATT_SESSION = "user";
    private static final String ATT_LISTCONTACTS = "listContacts";
    private static final String PATH_LIST_CONTACT = "/user/contacts";
    private static final String VIEW_LISTCONTACTS = "/WEB-INF/views/contacts.jsp";
    private static final String VIEW_FORMCONTACT = "/WEB-INF/views/formContact.jsp";
    @EJB
    private ContactService contactService;
    @EJB
    private ConversationService conversationService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> errors = new ArrayList();
        String action = request.getParameter("action");
        String contactId = request.getParameter("id");
        boolean idContactOk = false;
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_LISTCONTACTS);
        User userLogged = (User) request.getSession().getAttribute(ATT_SESSION);
        if (contactId != null && !contactId.isEmpty()) {
            idContactOk = true;
        }
        // If param action (add, edit, delete)
        if (action != null && !action.isEmpty() && (action.equals("edit") || action.equals("add") || action.equals("delete"))) {
            if (action.equals("add")) {
                request.setAttribute("action", action);
                dispatcher = request.getRequestDispatcher(VIEW_FORMCONTACT);
            }
            if (action.equals("edit")) {
                Contact contact = null;
                if (idContactOk) {
                    request.setAttribute("action", action);
                    request.setAttribute("contactId", contactId);
                    try {
                        contact = contactService.getContactById(contactId);
                    } catch (Exception e) {
                        errors.add(e.getMessage());
                    }
                    if (contact == null) {
                        errors.add("This contact doesn't exist.");
                    } else {
                        request.setAttribute("phoneNumber", contact.getPhoneNumber());
                        request.setAttribute("firstName", contact.getFirstName());
                        request.setAttribute("lastName", contact.getLastName());
                        request.setAttribute("postalAddress", contact.getPostalAddress());
                        request.setAttribute("email", contact.getEmail());
                    }
                    dispatcher = request.getRequestDispatcher(VIEW_FORMCONTACT);
                }
            }
            if (action.equals("delete")) {
                // Retrieve contact phone number to delete
                String phoneContact = contactService.getContactById(contactId).getPhoneNumber();
                // User logged conversations
                List<Conversation> listConv = conversationService.getConversationByUser(userLogged);
                for (Conversation c : listConv) {
                    for (String tel : c.getPhoneNumber()) {
                        // Delete the conversation with the contact, before delete the contact
                        if (tel.equals(phoneContact)) {
                            conversationService.removeConversation(c.getId().toString());
                        }
                    }
                }
                // Now we can remove the contact.
                try {
                    contactService.removeContact(contactId);
                } catch (Exception e) {
                    errors.add(e.getMessage());
                }
                if (errors.isEmpty()) {
                    response.sendRedirect(request.getHeader("referer"));
                    return;
                }
            }
        }
        // No param action -> just display list contacts 
        else {
            List<Contact> listContacts = contactService.getAllContactsFromUser(userLogged);
            request.setAttribute(ATT_LISTCONTACTS, listContacts);
        }
        request.setAttribute("errors", errors);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(VIEW_FORMCONTACT);
        List<String> errors = new ArrayList();
        User userLogged = (User) request.getSession().getAttribute(ATT_SESSION);
        Contact contact;
        String action = request.getParameter("action");
        //On recupere les informations du forumulaire
        String phoneNumber = request.getParameter("phoneNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String postalAddress = request.getParameter("postalAddress");
        //On stocke les attributs en cas d'erreur
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("email", email);
        request.setAttribute("postalAddress", postalAddress);
        if (action.equals("add")) {
            //Create contact
            contact = new Contact();
            try {
                contact.setPhoneNumber(phoneNumber);
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setEmail(email);
                contact.setPostalAddress(postalAddress);
                contact.setUser(userLogged);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
            if (errors.isEmpty()) {
                contactService.addContact(contact);
                response.sendRedirect(request.getContextPath() + PATH_LIST_CONTACT);
                return;
            }
        }
        if (action.equals("edit")) {
            String contactId = request.getParameter("contactId");
            contact = contactService.getContactById(contactId);
            try {
                contact.setPhoneNumber(phoneNumber);
                contact.setFirstName(firstName);
                contact.setLastName(lastName);
                contact.setPostalAddress(postalAddress);
                contact.setEmail(email);
            } catch (Exception e) {
                errors.add(e.getMessage());
            }
            if (errors.isEmpty()) {
                contactService.updateContact(contact);
                response.sendRedirect(request.getContextPath() + PATH_LIST_CONTACT);
                return;
            }
        }
        // Finish. Attach errors and forward.
        request.setAttribute("errors", errors);
        dispatcher.forward(request, response);
    }
}
