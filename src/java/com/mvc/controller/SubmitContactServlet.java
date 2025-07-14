package com.mvc.controller;

import com.mvc.bean.ContactBean;
import com.mvc.dao.ContactDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SubmitContactServlet")
public class SubmitContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String header = request.getParameter("contactHeader");
        String description = request.getParameter("contactDescription");
        String extra = request.getParameter("contactExtra");

        ContactBean bean = new ContactBean();
        bean.setContactHeader(header);
        bean.setContactDescription(description);
        bean.setContactExtra(extra);

        boolean result = ContactDao.insertContact(bean);

        if (result) {
            response.sendRedirect("DashboardAdminServlet"); // back to admin dashboard
        } else {
            response.sendRedirect("addContact.jsp?error=true");
        }
    }
}
