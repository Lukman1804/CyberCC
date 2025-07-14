package com.mvc.controller;

import com.mvc.bean.ReportBean;
import com.mvc.bean.ArticleBean;
import com.mvc.bean.ResourceBean;
import com.mvc.bean.ContactBean;

import com.mvc.dao.ReportDao;
import com.mvc.dao.ArticleDao;
import com.mvc.dao.ResourceDao;
import com.mvc.dao.ContactDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/SubmitReportServlet")
public class SubmitReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get form data
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int clientID = Integer.parseInt(request.getParameter("clientID"));
        String reportDateStr = request.getParameter("reportDate");
        int adminId = 1;

        // Convert String to java.sql.Date
        Date reportDate = Date.valueOf(reportDateStr); // Format must be yyyy-MM-dd

        // Create and populate the ReportBean
        ReportBean report = new ReportBean();
        report.setReportTitle(title);
        report.setReportDescription(description);
        report.setClientID(clientID);
        report.setReportDate(reportDate);
        report.setAdminID(adminId);
        report.setReportApproval("Pending"); // default unapproved

        // Insert report using DAO
        boolean result = ReportDao.insertReport(report);

        // Redirect or forward based on result
        if (result) {
            // Load updated data
            List<ArticleBean> articles = ArticleDao.getAllArticles();
            List<ReportBean> reports = ReportDao.getAllReports();
            List<ResourceBean> resources = ResourceDao.getAllResources();
            List<ContactBean> contacts = ContactDao.getAllContacts();

            request.setAttribute("articles", articles);
            request.setAttribute("reports", reports);
            request.setAttribute("resources", resources);
            request.setAttribute("contacts", contacts);

            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Failed to submit report. Please try again.");
            request.getRequestDispatcher("writeReport.jsp").forward(request, response);
        }
    }
}
