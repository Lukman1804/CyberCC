<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    int clientID = (Integer) s.getAttribute("ID"); // Update if your attribute name differs
    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Write New Article</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .form-container {
            background-color: #3a5998;
            padding: 30px 40px;
            border-radius: 10px;
            width: 400px;
            color: white;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 5px;
            margin-bottom: 15px;
            font-size: 1em;
        }

        textarea {
            resize: vertical;
            height: 100px;
        }

        .submit-btn {
            background-color: #6aabe7;
            color: white;
            padding: 10px 16px;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #4e93d2;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>✍️ Write a New Article</h2>
    <form action="SubmitArticleServlet" method="post">
        <label for="title">Article Title:</label>
        <input type="text" id="title" name="title" required>

        <label for="description">Article Description:</label>
        <textarea id="description" name="description" required></textarea>

        <!-- Hidden fields for client ID and date -->
        <input type="hidden" name="clientID" value="<%= clientID %>">
        <input type="hidden" name="articleDate" value="<%= currentDate %>">

        <button type="submit" class="submit-btn">Submit Article</button>
    </form>
</div>

</body>
</html>
