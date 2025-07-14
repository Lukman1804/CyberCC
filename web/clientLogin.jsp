<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Client Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 3em;
        }
        form {
            width: 300px;
            padding: 2em;
            border: 1px solid #ccc;
            border-radius: 5px;
            background: #f9f9f9;
        }
        label {
            display: block;
            margin-top: 1em;
        }
        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 0.5em;
            margin-top: 0.5em;
        }
        input[type="submit"] {
            margin-top: 1.5em;
            padding: 0.5em 1em;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>

    <h2>Client Login</h2>

    <form action="LoginServlet" method="post">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        
        <input type="hidden" name="role" value="client">

        <input type="submit" value="Login">
    </form>

    <%-- Show error message if it exists --%>
    <%
        String error = (String) request.getAttribute("errMessage");
        if (error != null) {
    %>
        <p class="error"><%= error %></p>
    <%
        }
    %>

</body>
</html>
