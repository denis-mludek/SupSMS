package com.supsms.filters;

import com.supsms.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter implements Filter {
    public static final String ATT_SESSION = "user";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = req.getSession();
            User user = null;
            user = (User) session.getAttribute(ATT_SESSION);
            System.out.println("User in session : " + user);
            if (user == null || !user.isAdminBool()) {
                res.sendRedirect(req.getContextPath() + "/accessRefused");
            } else {
                chain.doFilter(req, res);
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }
}
