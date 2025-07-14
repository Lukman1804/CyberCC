package com.mvc.controller;

import com.mvc.bean.ResourceBean;
import com.mvc.dao.ResourceDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/SubmitResourceServlet")
public class SubmitResourceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String header = request.getParameter("resourceHeader");
        String description = request.getParameter("resourceDescription");
        String link = request.getParameter("resourceLink");
        String date = request.getParameter("resourceDate");

        ResourceBean bean = new ResourceBean();
        bean.setResourceHeader(header);
        bean.setResourceDescription(description);
        bean.setResourceLink(link);

        boolean result = ResourceDao.insertResource(bean);

        if (result) {
            response.sendRedirect("DashboardAdminServlet"); // redirect back to dashboard
        } else {
            response.sendRedirect("addResource.jsp?error=true");
        }
    }
}
