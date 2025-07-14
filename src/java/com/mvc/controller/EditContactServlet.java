package com.mvc.controller;

import com.mvc.bean.ContactBean;
import com.mvc.dao.ContactDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EditContactServlet")
public class EditContactServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int contactId = Integer.parseInt(idStr);
                ContactDao dao = new ContactDao();
                ContactBean contact = dao.getContactById(contactId);

                if (contact != null) {
                    request.setAttribute("contact", contact);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editContact.jsp");
                    dispatcher.forward(request, response);
                    return;
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("dashboardAdmin.jsp");
    }
}
