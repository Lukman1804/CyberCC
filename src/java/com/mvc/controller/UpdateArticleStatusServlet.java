package com.mvc.controller;

import com.mvc.dao.ArticleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/UpdateArticleStatusServlet")
public class UpdateArticleStatusServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int articleID = Integer.parseInt(request.getParameter("articleID"));
            String status = request.getParameter("status");

            HttpSession session = request.getSession(false);

            if (session == null || session.getAttribute("ID") == null) {
                response.sendRedirect("index.jsp");
                return;
            }

            int adminID = (int) session.getAttribute("ID");

            boolean success = ArticleDao.updateArticleStatus(articleID, status, adminID);

            if (success) {
                response.sendRedirect("approveArticle.jsp?update=success");
            } else {
                response.sendRedirect("approveArticle.jsp?update=failure");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("approveArticle.jsp?error=invalid_id");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("approveArticle.jsp?error=server_error");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
