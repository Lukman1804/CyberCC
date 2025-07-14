package com.mvc.controller;

import com.mvc.dao.ArticleDao;
import com.mvc.bean.ArticleBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateArticleServlet")
public class UpdateArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form values
        String articleID = request.getParameter("articleID");
        String title = request.getParameter("articleTitle");
        String description = request.getParameter("articleDescription");

        // Create article bean and set updated values
        ArticleBean article = new ArticleBean();
        article.setArticleID(Integer.parseInt(articleID));
        article.setArticleTitle(title);
        article.setArticleDescription(description);

        // Call DAO to update
        boolean updated = ArticleDao.updateArticle(article);

        if (updated) {
            // Set success message and redirect
            request.setAttribute("message", "Article updated successfully.");
        } else {
            request.setAttribute("message", "Failed to update article.");
        }

        request.getRequestDispatcher("myArticles.jsp").forward(request, response);
    }
}
