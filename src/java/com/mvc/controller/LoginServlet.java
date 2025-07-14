package com.mvc.controller;

import com.mvc.bean.*;
import com.mvc.dao.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        LoginBean loginBean = new LoginBean();
        loginBean.setEmail(email);
        loginBean.setPassword(password);
        loginBean.setRole(role);

        LoginDao loginDao = new LoginDao();
        LoginBean authenticatedUser = loginDao.authenticateUser(loginBean);

        if (authenticatedUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("email", authenticatedUser.getEmail());
            session.setAttribute("name", authenticatedUser.getName());
            session.setAttribute("ID", authenticatedUser.getID());
            session.setAttribute("role", authenticatedUser.getRole());

            if ("admin".equals(authenticatedUser.getRole())) {
                List<ArticleBean> articles = ArticleDao.getAllArticles();
                List<ReportBean> reports = ReportDao.getAllReports();
                List<ResourceBean> resources = ResourceDao.getAllResources();
                List<ContactBean> contacts = ContactDao.getAllContacts();

                request.setAttribute("articles", articles);
                request.setAttribute("reports", reports);
                request.setAttribute("resources", resources);
                request.setAttribute("contacts", contacts);
                
                request.getRequestDispatcher("/dashboardAdmin.jsp").forward(request, response);
            } else {
                List<ArticleBean> articles = ArticleDao.getAllArticles();
                List<ReportBean> reports = ReportDao.getAllReports();
                List<ResourceBean> resources = ResourceDao.getAllResources();
                List<ContactBean> contacts = ContactDao.getAllContacts();

                request.setAttribute("articles", articles);
                request.setAttribute("reports", reports);
                request.setAttribute("resources", resources);
                request.setAttribute("contacts", contacts);

                request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errMessage", "Invalid user credentials");
            request.getRequestDispatcher("/clientLogin.jsp").forward(request, response);
        }
    }
}
