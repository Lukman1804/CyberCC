package com.mvc.controller;

import com.mvc.bean.ReportBean;
import com.mvc.dao.ReportDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditReportServlet")
public class EditReportServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportIDStr = request.getParameter("reportID");

        if (reportIDStr != null && !reportIDStr.isEmpty()) {
            try {
                int reportID = Integer.parseInt(reportIDStr);
                ReportBean report = ReportDao.getReportById(reportID);

                if (report != null) {
                    request.setAttribute("report", report);
                    request.getRequestDispatcher("editReport.jsp").forward(request, response);
                    return;
                } else {
                    request.getSession().setAttribute("message", "Report not found.");
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
