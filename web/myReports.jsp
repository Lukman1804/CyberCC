<%@page import="com.mvc.dao.ReportDao"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.bean.ReportBean"%>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("email") == null || s.getAttribute("ID") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    int clientID = Integer.parseInt(s.getAttribute("ID").toString());
    List<ReportBean> reports = ReportDao.getAllReports();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Reports</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; max-width: 900px; margin: auto; }
        h1 { text-align: center; }

        /* Swapped color scheme */
        .card:nth-child(odd) { background-color: #b33939; }
        .card:nth-child(even) { background-color: #e55039; }

        .card {
            display: flex; justify-content: space-between; align-items: flex-start;
            margin-bottom: 30px; border-radius: 8px; padding: 20px; color: #fff;
        }
        .card-text { flex: 3; }
        .card-meta { flex: 1; text-align: right; font-size: 0.9em; padding-left: 20px; }

        .btn-report {
            padding: 6px 14px;
            margin-left: 6px;
            border-radius: 5px;
            font-weight: bold;
            font-size: 0.9em;
            border: none;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-report.edit {
            background-color: #b33939;
            color: white;
        }
        .btn-report.edit:hover {
            background-color: #902828;
        }
        .btn-report.delete {
            background-color: #701c1c;
            color: white;
        }
        .btn-report.delete:hover {
            background-color: #4f0e0e;
        }
    </style>
</head>
<body>
<h1>📄 My Reports</h1>

<%
    boolean found = false;
    for (ReportBean report : reports) {
        if (report.getClientID() == clientID) {
            found = true;
%>
    <div class="card">
        <div class="card-text">
            <h3>🚨 <%= report.getReportTitle() %></h3>
            <p><%= report.getReportDescription() %></p>
        </div>
        <div class="card-meta">
            <p><strong>Date:</strong> <%= report.getReportDate() %></p>
            <p><strong>Status:</strong> <%= report.getReportApproval() %></p>
        </div>
    </div>
    <div style="text-align: right; margin-top: -20px; margin-bottom: 30px;">
        <form action="DeleteReportServlet" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete this report?');">
            <input type="hidden" name="reportID" value="<%= report.getReportID() %>"/>
            <button type="submit" class="btn-report delete">🗑️ Delete</button>
        </form>
        <form action="EditReportServlet" method="get" style="display:inline;">
            <input type="hidden" name="reportID" value="<%= report.getReportID() %>"/>
            <button type="submit" class="btn-report edit">✏️ Edit</button>
        </form>
    </div>
<%
        }
    }

    if (!found) {
%>
    <p>No reports found for your account.</p>
<%
    }
%>

</body>
</html>
