<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mvc.bean.ReportBean" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    ReportBean report = (ReportBean) request.getAttribute("report");
    if (report == null) {
        out.println("<p>No report found to edit.</p>");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px auto;
            max-width: 700px;
        }

        h1 {
            text-align: center;
            color: #b33939;
        }

        form {
            background: #b33939; /* Even report card color */
            padding: 25px;
            border-radius: 8px;
            color: white;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
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
            font-size: 1em;
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
            transition: background-color 0.3s ease;
        }

        .btn.cancel {
            background-color: #ccc;
            color: black;
            text-decoration: none;
        }

        .btn.cancel:hover {
            background-color: #aaa;
        }

        .btn.update {
            background-color: #e55039; /* Odd report card color */
            color: white;
        }

        .btn.update:hover {
            background-color: #c03a2b; /* Hover for odd color */
        }
    </style>
</head>
<body>

<h1>✏️ Edit Report</h1>

<form action="UpdateReportServlet" method="post">
    <input type="hidden" name="reportID" value="<%= report.getReportID() %>"/>

    <label for="title">Report Title:</label>
    <input type="text" id="title" name="reportTitle" required value="<%= report.getReportTitle() %>"/>

    <label for="description">Report Description:</label>
    <textarea id="description" name="reportDescription" required><%= report.getReportDescription() %></textarea>

    <div class="form-buttons">
        <a href="myReports.jsp" class="btn cancel">❌ Cancel</a>
        <button type="submit" class="btn update">💾 Update</button>
    </div>
</form>

</body>
</html>
