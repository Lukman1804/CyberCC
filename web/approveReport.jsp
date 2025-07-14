<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mvc.bean.ReportBean" %>
<%@ page import="com.mvc.dao.ReportDao" %>
<%
    HttpSession s = request.getSession(false);
    if (s == null || s.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    List<ReportBean> pendingReports = ReportDao.getPendingReports();
    String updateStatus = request.getParameter("update");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Report Approval</title>
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

        /* Report card color scheme */
        .card:nth-child(odd) {
            background-color: #e55039;
        }
        .card:nth-child(even) {
            background-color: #b33939;
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

        .no-reports {
            text-align: center;
            font-style: italic;
            color: #333;
            margin-top: 50px;
        }
    </style>
</head>
<body>

<h1>🛠️ Manage Pending Reports</h1>

<% if ("success".equals(updateStatus)) { %>
    <div class="alert-success">
        ✅ Report status updated successfully.
    </div>
<% } %>

<%
    if (pendingReports != null && !pendingReports.isEmpty()) {
        for (ReportBean report : pendingReports) {
%>
    <div class="card">
        <div class="card-text">
            <h3>🚨 <%= report.getReportTitle() %></h3>
            <p><%= report.getReportDescription() %></p>
        </div>
        <div class="card-meta">
            <p><strong>Reported by:</strong> <%= report.getClientName() %></p>
            <p><strong>Date:</strong> <%= report.getReportDate() %></p>
            <form class="status-form" action="UpdateReportStatusServlet" method="post">
                <input type="hidden" name="reportID" value="<%= report.getReportID() %>" />
                <select name="status">
                    <option value="pending" <%= "pending".equals(report.getReportApproval()) ? "selected" : "" %>>Pending</option>
                    <option value="approved" <%= "approved".equals(report.getReportApproval()) ? "selected" : "" %>>Approved</option>
                    <option value="disapproved" <%= "disapproved".equals(report.getReportApproval()) ? "selected" : "" %>>Disapproved</option>
                </select>
                <button type="submit" class="update-btn">Update</button>
            </form>
        </div>
    </div>
<%
        }
    } else {
%>
    <div class="no-reports">No pending reports to manage.</div>
<%
    }
%>

</body>
</html>
