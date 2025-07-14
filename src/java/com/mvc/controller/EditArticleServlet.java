package com.mvc.controller;

import com.mvc.bean.ArticleBean;
import com.mvc.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditArticleServlet")
public class EditArticleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String articleIDStr = request.getParameter("articleID");

        if (articleIDStr != null && !articleIDStr.isEmpty()) {
            try {
                int articleID = Integer.parseInt(articleIDStr);
                ArticleBean article = ArticleDao.getArticleById(articleID);

                if (article != null) {
                    request.setAttribute("article", article);
                    request.getRequestDispatcher("editArticle.jsp").forward(request, response);
                    return;
                } else {
                    request.getSession().setAttribute("message", "Article not found.");
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
