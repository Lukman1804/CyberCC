<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mvc.bean.ArticleBean" %>
<%@ page import="com.mvc.dao.ArticleDao" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    List<ArticleBean> pendingArticles = ArticleDao.getPendingArticles();
    String updateStatus = request.getParameter("update");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Article Approval</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 40px;
            background-color: #f5f5f5;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        /* Alert box */
        .alert-success {
            background-color: #d4edda;
            color: #155724;
            padding: 10px 20px;
            margin: 20px auto;
            border: 1px solid #c3e6cb;
            border-radius: 4px;
            max-width: 800px;
            text-align: center;
        }

        /* Article card color scheme */
        .card:nth-child(odd) {
            background-color: #6aabe7;
        }
        .card:nth-child(even) {
            background-color: #3a5998;
        }

        .card {
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            margin-bottom: 30px;
            border-radius: 8px;
            padding: 20px;
            max-width: 900px;
            margin-left: auto;
            margin-right: auto;
        }

        .card-text {
            flex: 3;
        }

        .card-meta {
            flex: 1;
            font-size: 0.9em;
            text-align: right;
            padding-left: 20px;
        }

        .card h3 {
            margin-top: 0;
        }

        .card p {
            margin-bottom: 8px;
        }

        .card:hover {
            filter: brightness(1.1);
        }

        .status-form {
            margin-top: 12px;
            display: flex;
            gap: 10px;
            align-items: center;
        }

        .status-form select {
            padding: 6px 10px;
            border-radius: 4px;
            border: none;
            font-size: 0.9em;
        }

        .update-btn {
            background-color: #ffffff;
            color: #333;
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
        }

        .update-btn:hover {
            background-color: #e0e0e0;
        }

        .no-articles {
            text-align: center;
            font-style: italic;
            color: #333;
            margin-top: 50px;
        }
    </style>
</head>
<body>

<h1>🛠️ Manage Pending Articles</h1>

<% if ("success".equals(updateStatus)) { %>
    <div class="alert-success">
        ✅ Article status updated successfully.
    </div>
<% } %>

<%
    if (pendingArticles != null && !pendingArticles.isEmpty()) {
        for (ArticleBean article : pendingArticles) {
%>
    <div class="card">
        <div class="card-text">
            <h3>📰 <%= article.getArticleTitle() %></h3>
            <p><%= article.getArticleDescription() %></p>
        </div>
        <div class="card-meta">
            <p><strong>Writer:</strong> <%= article.getClientName() %></p>
            <p><strong>Date:</strong> <%= article.getArticleDate() %></p>
            <form class="status-form" action="UpdateArticleStatusServlet" method="post">
                <input type="hidden" name="articleID" value="<%= article.getArticleID() %>" />
                <select name="status">
                    <option value="pending" <%= "pending".equals(article.getArticleApproval()) ? "selected" : "" %>>Pending</option>
                    <option value="approved" <%= "approved".equals(article.getArticleApproval()) ? "selected" : "" %>>Approved</option>
                    <option value="disapproved" <%= "disapproved".equals(article.getArticleApproval()) ? "selected" : "" %>>Disapproved</option>
                </select>
                <button type="submit" class="update-btn">Update</button>
            </form>
        </div>
    </div>
<%
        }
    } else {
%>
    <div class="no-articles">No pending articles to manage.</div>
<%
    }
%>

</body>
</html>
