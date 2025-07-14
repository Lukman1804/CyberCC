package com.mvc.controller;

import com.mvc.dao.ContactDao;
import com.mvc.bean.ContactBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UpdateContactServlet")
public class UpdateContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("contactID"));
        String header = request.getParameter("header");
        String description = request.getParameter("description");
        String extra = request.getParameter("extra");

        ContactBean contact = new ContactBean();
        contact.setContactID(id);
        contact.setContactHeader(header);
        contact.setContactDescription(description);
        contact.setContactExtra(extra);

        ContactDao dao = new ContactDao();
        dao.updateContact(contact);

        response.sendRedirect("dashboardAdmin.jsp");
    }
}
