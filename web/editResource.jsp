<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mvc.bean.ResourceBean" %>
<%
    ResourceBean resource = (ResourceBean) request.getAttribute("resource");

    if (resource == null) {
        response.sendRedirect("dashboardAdmin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Resource</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px auto;
            max-width: 700px;
        }

        h1 {
            text-align: center;
            color: #2b8b63;
        }

        form {
            background: #2b8b63; /* Even resource card color */
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
            background-color: #5ec2a0; /* Odd resource card */
            color: white;
        }

        .btn.update:hover {
            background-color: #4bab89; /* Hover for odd */
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

<h1>✏️ Edit Resource</h1>

<form action="UpdateResourceServlet" method="post">
    <input type="hidden" name="resourceID" value="<%= resource.getResourceID() %>">

    <label for="header">Header:</label>
    <input type="text" id="header" name="header" value="<%= resource.getResourceHeader() %>" required>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required><%= resource.getResourceDescription() %></textarea>

    <label for="link">Link:</label>
    <input type="text" id="link" name="link" value="<%= resource.getResourceLink() %>" required>

    <div class="form-buttons">
        <a href="dashboardAdmin.jsp" class="btn cancel">❌ Cancel</a>
        <button type="submit" class="btn update">💾 Update</button>
    </div>
</form>

</body>
</html>
