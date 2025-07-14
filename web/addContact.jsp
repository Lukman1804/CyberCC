<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String adminID = (String) s.getAttribute("ID"); // Adjust name if different
    String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Contact</title>
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
            background-color: #e1b12c;
            padding: 30px 40px;
            border-radius: 10px;
            width: 400px;
            color: #222;
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
            background-color: #f6e58d;
            color: #222;
            padding: 10px 16px;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            width: 100%;
            transition: background-color 0.3s ease;
        }

        .submit-btn:hover {
            background-color: #f1d676;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>📞 Add New Contact</h2>
    <form action="SubmitContactServlet" method="post">
        <label for="header">Contact Header:</label>
        <input type="text" id="header" name="contactHeader" required>

        <label for="description">Contact Description:</label>
        <textarea id="description" name="contactDescription" required></textarea>

        <label for="extra">Additional Info (e.g., hours, number):</label>
        <input type="text" id="extra" name="contactExtra" required>

        <button type="submit" class="submit-btn">Submit Contact</button>
    </form>
</div>

</body>
</html>
