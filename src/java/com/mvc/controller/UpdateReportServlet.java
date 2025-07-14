package com.mvc.controller;

import com.mvc.dao.ReportDao;
import com.mvc.bean.ReportBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateReportServlet")
public class UpdateReportServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from form
        String reportID = request.getParameter("reportID");
        String title = request.getParameter("reportTitle");
        String description = request.getParameter("reportDescription");

        // Create and populate ReportBean
        ReportBean report = new ReportBean();
        report.setReportID(Integer.parseInt(reportID));
        report.setReportTitle(title);
        report.setReportDescription(description);

        // Update the report in the database
        boolean updated = ReportDao.updateReport(report);

        // Set success or error message (optional)
        if (updated) {
            request.setAttribute("message", "Report updated successfully.");
        } else {
            request.setAttribute("message", "Failed to update report.");
        }

        // Forward back to myReports.jsp
        request.getRequestDispatcher("myReports.jsp").forward(request, response);
    }
}
