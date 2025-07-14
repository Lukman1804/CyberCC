package com.mvc.controller;

import com.mvc.dao.ResourceDao;
import com.mvc.bean.ResourceBean;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UpdateResourceServlet")
public class UpdateResourceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("resourceID"));
        String header = request.getParameter("header");
        String description = request.getParameter("description");
        String link = request.getParameter("link");

        ResourceBean resource = new ResourceBean();
        resource.setResourceID(id);
        resource.setResourceHeader(header);
        resource.setResourceDescription(description);
        resource.setResourceLink(link);

        ResourceDao dao = new ResourceDao();
        dao.updateResource(resource);

        response.sendRedirect("dashboardAdmin.jsp");
    }
}
