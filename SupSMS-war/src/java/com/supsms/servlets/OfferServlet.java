/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supsms.servlets;

import com.supsms.service.OfferService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YemYem
 */
@WebServlet(name = "OfferServlet", urlPatterns = {"/offer"})
public class OfferServlet extends HttpServlet {
    public static final String VIEW_OFFER = "/WEB-INF/views/offer.jsp";
    @EJB
    private OfferService offerService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("offer", offerService.findOfferByName("Basic Offer"));
        request.getRequestDispatcher(VIEW_OFFER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
