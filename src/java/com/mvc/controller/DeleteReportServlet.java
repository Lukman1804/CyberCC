package com.mvc.controller;

import com.mvc.dao.ReportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteReportServlet")
public class DeleteReportServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportIDStr = request.getParameter("reportID");

        if (reportIDStr != null && !reportIDStr.isEmpty()) {
            try {
                int reportID = Integer.parseInt(reportIDStr);

                boolean success = ReportDao.deleteReport(reportID);

                if (success) {
                    request.getSession().setAttribute("message", "Report deleted successfully.");
                } else {
                    request.getSession().setAttribute("message", "Failed to delete the report.");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("message", "Invalid report ID.");
            }
        } else {
            request.getSession().setAttribute("message", "No report ID provided.");
        }

        response.sendRedirect("myReports.jsp");
    }
}
