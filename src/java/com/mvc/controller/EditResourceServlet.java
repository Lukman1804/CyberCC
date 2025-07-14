package com.mvc.controller;

import com.mvc.bean.ResourceBean;
import com.mvc.dao.ResourceDao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/EditResourceServlet")
public class EditResourceServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int resourceId = Integer.parseInt(idStr);
                ResourceDao dao = new ResourceDao();
                ResourceBean resource = dao.getResourceById(resourceId);

                if (resource != null) {
                    request.setAttribute("resource", resource);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("editResource.jsp");
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
