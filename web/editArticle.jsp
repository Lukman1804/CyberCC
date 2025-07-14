<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mvc.bean.ArticleBean" %>
<%
    ArticleBean article = (ArticleBean) request.getAttribute("article");

    if (article == null) {
        response.sendRedirect("myArticles.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Article</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px auto;
            max-width: 700px;
        }

        h1 {
            text-align: center;
            color: #3a5998;
        }

        form {
            background: #3a5998; /* Even article card color */
            padding: 25px;
            border-radius: 8px;
            color: white;
        }

        label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 6px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        textarea {
            height: 150px;
            resize: vertical;
        }

        .form-buttons {
            margin-top: 20px;
            text-align: right;
        }

        .btn {
            padding: 10px 18px;
            font-size: 1em;
            font-weight: bold;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-left: 10px;
        }

        .btn.update {
            background-color: #6aabe7; /* Odd article card color */
            color: white;
        }

        .btn.update:hover {
            background-color: #4e93d2; /* Hover effect for odd */
        }

        .btn.cancel {
            background-color: #ccc;
            color: black;
            text-decoration: none;
            display: inline-block;
        }

        .btn.cancel:hover {
            background-color: #aaa;
        }
    </style>
</head>
<body>

<h1>✏️ Edit Article</h1>

<form action="UpdateArticleServlet" method="post">
    <input type="hidden" name="articleID" value="<%= article.getArticleID() %>">

    <label for="title">Title:</label>
    <input type="text" id="title" name="articleTitle" value="<%= article.getArticleTitle() %>" required>

    <label for="description">Description:</label>
    <textarea id="description" name="articleDescription" required><%= article.getArticleDescription() %></textarea>

    <div class="form-buttons">
        <a href="myArticles.jsp" class="btn cancel">❌ Cancel</a>
        <button type="submit" class="btn update">💾 Update</button>
    </div>
</form>

</body>
</html>
