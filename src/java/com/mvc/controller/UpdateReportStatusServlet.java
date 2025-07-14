package com.mvc.controller;

import com.mvc.dao.ReportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateReportStatusServlet")
public class UpdateReportStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int reportID = Integer.parseInt(request.getParameter("reportID"));
        String status = request.getParameter("status");

        HttpSession session = request.getSession(false);
        int adminID = (int) session.getAttribute("ID");

        ReportDao.updateReportStatus(reportID, status, adminID);
        response.sendRedirect("approveReport.jsp?update=success");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
