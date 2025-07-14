package com.mvc.controller;

import com.mvc.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String articleIDStr = request.getParameter("articleID");

        if (articleIDStr != null && !articleIDStr.isEmpty()) {
            try {
                int articleID = Integer.parseInt(articleIDStr);

                boolean success = ArticleDao.deleteArticle(articleID);

                if (success) {
                    request.getSession().setAttribute("message", "Article deleted successfully.");
                } else {
                    request.getSession().setAttribute("message", "Failed to delete the article.");
                }
            } catch (NumberFormatException e) {
                request.getSession().setAttribute("message", "Invalid article ID.");
            }
        } else {
            request.getSession().setAttribute("message", "No article ID provided.");
        }

        response.sendRedirect("myArticles.jsp");
    }
}
