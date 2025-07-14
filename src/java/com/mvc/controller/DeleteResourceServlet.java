package com.mvc.controller;

import com.mvc.dao.ResourceDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/DeleteResourceServlet")
public class DeleteResourceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int resourceId = Integer.parseInt(idStr);
                ResourceDao resourceDao = new ResourceDao();
                resourceDao.deleteResourceById(resourceId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("dashboardAdmin.jsp");
    }
}
