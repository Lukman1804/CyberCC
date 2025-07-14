<%@page import="com.mvc.dao.ArticleDao"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mvc.bean.ArticleBean" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    Integer clientID = (Integer) s.getAttribute("ID");
    List<ArticleBean> articles = (List<ArticleBean>) ArticleDao.getAllArticles();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Articles</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; max-width: 900px; margin: auto; }
        h1 { text-align: center; }

        .card:nth-child(odd) { background-color: #3a5998; }
        .card:nth-child(even) { background-color: #6aabe7; }

        .card {
            display: flex; justify-content: space-between; align-items: flex-start;
            margin-bottom: 30px; border-radius: 8px; padding: 20px; color: #fff;
        }
        .card-text { flex: 3; }
        .card-meta { flex: 1; text-align: right; font-size: 0.9em; padding-left: 20px; }

        .btn-article {
            padding: 6px 14px;
            margin-left: 6px;
            border-radius: 5px;
            font-weight: bold;
            font-size: 0.9em;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-article.edit { background-color: #3a5998; color: white; }
        .btn-article.edit:hover { background-color: #2c4377; }
        .btn-article.delete { background-color: #902828; color: white; }
        .btn-article.delete:hover { background-color: #6e1e1e; }
    </style>
</head>
<body>
<h1>📚 My Articles</h1>

<%
    boolean hasArticles = false;

    if (articles != null && !articles.isEmpty()) {
        for (ArticleBean article : articles) {
            if (clientID != null && clientID == article.getClientID()) {
                hasArticles = true;
%>
    <div class="card">
        <div class="card-text">
            <h3>📰 <%= article.getArticleTitle() %></h3>
            <p><%= article.getArticleDescription() %></p>
        </div>
        <div class="card-meta">
            <p><strong>Date:</strong> <%= article.getArticleDate() %></p>
            <p><strong>Status:</strong> <%= article.getArticleApproval() %></p>
        </div>
    </div>
    <div style="text-align: right; margin-top: -20px; margin-bottom: 30px;">
        <form action="DeleteArticleServlet" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this article?');">
            <input type="hidden" name="articleID" value="<%= article.getArticleID() %>"/>
            <button type="submit" class="btn-article delete">🗑️ Delete</button>
        </form>
        <form action="EditArticleServlet" method="get" style="display:inline;">
            <input type="hidden" name="articleID" value="<%= article.getArticleID() %>"/>
            <button type="submit" class="btn-article edit">✏️ Edit</button>
        </form>
    </div>
<%
            }
        }
    }

    if (!hasArticles) {
%>
    <p>No articles found for your account.</p>
<%
    }
%>
</body>
</html>
