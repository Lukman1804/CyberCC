package com.mvc.controller;

import com.mvc.bean.ArticleBean;
import com.mvc.bean.ReportBean;
import com.mvc.bean.ResourceBean;
import com.mvc.bean.ContactBean;
import com.mvc.dao.ArticleDao;
import com.mvc.dao.ReportDao;
import com.mvc.dao.ResourceDao;
import com.mvc.dao.ContactDao;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            response.sendRedirect("index.jsp"); // redirect if not logged in
            return;
        }

        List<ArticleBean> articles = ArticleDao.getAllArticles();
        List<ReportBean> reports = ReportDao.getAllReports();
        List<ResourceBean> resources = ResourceDao.getAllResources();
        List<ContactBean> contacts = ContactDao.getAllContacts();

        request.setAttribute("articles", articles);
        request.setAttribute("reports", reports);
        request.setAttribute("resources", resources);
        request.setAttribute("contacts", contacts);

        String showSection = request.getParameter("show");
        if (showSection != null) {
            request.setAttribute("showSection", showSection); // this will trigger which tab to show
        }

        RequestDispatcher rd = request.getRequestDispatcher("dashboard.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
}
