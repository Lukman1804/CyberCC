package com.mvc.controller;

import com.mvc.dao.ContactDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DeleteContactServlet")
public class DeleteContactServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int contactId = Integer.parseInt(idStr);
                ContactDao contactDao = new ContactDao();
                contactDao.deleteContactById(contactId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("dashboardAdmin.jsp");
    }
}
