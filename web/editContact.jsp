<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mvc.bean.ContactBean" %>
<%
    ContactBean contact = (ContactBean) request.getAttribute("contact");

    if (contact == null) {
        response.sendRedirect("dashboardAdmin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Contact</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px auto;
            max-width: 700px;
        }

        h1 {
            text-align: center;
            color: #c49014;
        }

        form {
            background: #c49014; /* Even contact card color */
            padding: 25px;
            border-radius: 8px;
            color: #222;
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
            background-color: #e1b12c; /* Odd contact card */
            color: #222;
        }

        .btn.update:hover {
            background-color: #b98e10;
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

<h1>✏️ Edit Contact</h1>

<form action="UpdateContactServlet" method="post">
    <input type="hidden" name="contactID" value="<%= contact.getContactID() %>">

    <label for="header">Header:</label>
    <input type="text" id="header" name="header" value="<%= contact.getContactHeader() %>" required>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required><%= contact.getContactDescription() %></textarea>

    <label for="extra">Extra Details:</label>
    <input type="text" id="extra" name="extra" value="<%= contact.getContactExtra() %>" required>

    <div class="form-buttons">
        <a href="dashboardAdmin.jsp" class="btn cancel">❌ Cancel</a>
        <button type="submit" class="btn update">💾 Update</button>
    </div>
</form>

</body>
</html>
