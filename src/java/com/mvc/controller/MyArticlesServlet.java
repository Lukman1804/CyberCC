package com.mvc.controller;

import com.mvc.bean.ArticleBean;
import com.mvc.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/MyArticlesServlet")
public class MyArticlesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("clientID") == null) {
            response.sendRedirect("index.jsp"); // Not logged in
            return;
        }

        // Optional: you can log or print to console for debugging
        int clientID = (int) session.getAttribute("clientID");

        // Fetch all articles
        List<ArticleBean> allArticles = ArticleDao.getAllArticles();

        // Send the full list to JSP
        request.setAttribute("articles", allArticles);
        request.getRequestDispatcher("myArticles.jsp").forward(request, response);
    }
}
