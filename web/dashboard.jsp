<%@page import="com.mvc.dao.ArticleDao"%>
<%@page import="com.mvc.dao.ReportDao"%>
<%@page import="com.mvc.dao.ResourceDao"%>
<%@page import="com.mvc.dao.ContactDao"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mvc.bean.ArticleBean" %>
<%@ page import="com.mvc.bean.ReportBean" %>
<%@ page import="com.mvc.bean.ResourceBean" %>
<%@ page import="com.mvc.bean.ContactBean" %>
<%
    HttpSession s = request.getSession(false);  // get session, don't create new one
    if (s == null || s.getAttribute("email") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    String name = (String) s.getAttribute("name");
    List<ArticleBean> articles = ArticleDao.getAllArticles();
    List<ReportBean> reports = ReportDao.getAllReports();
    List<ResourceBean> resources = ResourceDao.getAllResources();
    List<ContactBean> contacts = ContactDao.getAllContacts();
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cybercrime Awareness Forum</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; }
        .header {
            background: #4a4a4a; color: #ddd; padding: 20px; text-align: center; position: relative;
        }
        .user-info {
            position: absolute; top: 20px; right: 20px; display: flex; align-items: center; gap: 15px;
            font-size: 1rem; font-weight: bold; color: #eee;
        }
        .logout-btn {
            background-color: #6a6a6a; border: none; padding: 6px 10px; border-radius: 50%;
            cursor: pointer; font-weight: normal; transition: background-color 0.3s ease;
            display: flex; align-items: center; justify-content: center; width: 36px; height: 36px;
        }
        .logout-btn:hover { background-color: #8c8c8c; }
        .logout-btn svg { fill: white; width: 18px; height: 18px; }

        .navbar {
            display: flex; background-color: #5e5e5e; justify-content: center;
        }
        .navbar a {
            color: #ddd; padding: 14px 20px; text-decoration: none; text-align: center; cursor: pointer;
        }
        .navbar a:hover, .navbar a.active { background-color: #7a7a7a; }

        .main-content { padding: 40px; max-width: 900px; margin: auto; }
        .section { display: none; }
        .section.active { display: block; }

        .card:nth-child(odd) { background-color: #6aabe7; }
        .card:nth-child(even) { background-color: #3a5998; }
        #report .card:nth-child(odd) { background-color: #e55039; }
        #report .card:nth-child(even) { background-color: #b33939; }
        #resources .card:nth-child(odd) { background-color: #2b8b63; }
        #resources .card:nth-child(even) { background-color: #5ec2a0; }
        #contact .card:nth-child(odd) { background-color: #e1b12c; color: #222; }
        #contact .card:nth-child(even) { background-color: #f6e58d; color: #222; }

        .card {
            display: flex; justify-content: space-between; align-items: flex-start;
            margin-bottom: 30px; border-radius: 8px; padding: 20px; color: #fff; text-decoration: none;
        }
        .card-text { flex: 3; }
        .card-meta { flex: 1; font-size: 0.9em; text-align: right; padding-left: 20px; }
        .card h3 { margin-top: 0; }
        .card:hover { filter: brightness(1.1); }

        #contact a { color: #222 !important; text-decoration: underline; }

        .write-btn {
            color: white;
            padding: 8px 14px;
            border-radius: 4px;
            text-decoration: none;
            font-weight: bold;
            font-size: 0.9em;
            transition: background-color 0.3s ease;
        }
        #articles .write-btn {
            background-color: #3a5998;
        }
        #articles .write-btn:hover {
            background-color: #2c4377;
        }
        #report .write-btn {
            background-color: #b33939;
        }
        #report .write-btn:hover {
            background-color: #902828;
        }
    </style>
</head>
<body>

<div class="header">
    <h1>Cybercrime Awareness Forum</h1>
    <p>Aware. Beware. Share.</p>
    <div class="user-info">
        <strong><%= name != null ? name : "User" %></strong>
        <form action="logout.jsp" method="post" style="margin:0;">
            <button class="logout-btn" title="Log Out" aria-label="Log Out">
                <svg viewBox="0 0 24 24" aria-hidden="true"><path d="M13 3h-2v10h2V3zm-1 18c-4.97 0-9-4.03-9-9H2c0 5.52 4.48 10 10 10s10-4.48 10-10h-1c0 4.97-4.03 9-9 9z"/></svg>
            </button>
        </form>
    </div>
</div>

<div class="navbar">
    <a id="nav-articles" onclick="showSection('articles', this)" class="active">Awareness Articles</a>
    <a id="nav-report" onclick="showSection('report', this)">Report Cybercrime</a>
    <a id="nav-resources" onclick="showSection('resources', this)">Resources</a>
    <a id="nav-contact" onclick="showSection('contact', this)">Contact</a>
</div>

<div class="main-content">

    <!-- Articles Section -->
    <div id="articles" class="section active">
        <div style="text-align: right; margin-bottom: 20px;">
            <a href="myArticles.jsp" class="write-btn" style="margin-right: 10px;">📚 My Articles</a>
            <a href="writeArticle.jsp" class="write-btn">✍️ Write Article</a>
        </div>
        <%
            if (articles != null && !articles.isEmpty()) {
                for (ArticleBean article : articles) {
        %>
        <div class="card">
            <div class="card-text">
                <h3>📰 <%= article.getArticleTitle() %></h3>
                <p><%= article.getArticleDescription() %></p>
            </div>
            <div class="card-meta">
                <p><strong>Writer:</strong> <%= article.getClientName() %></p>
                <p><strong>Date:</strong> <%= article.getArticleDate() %></p>
                <p><strong>Approved by:</strong> <%= article.getAdminName() %></p>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No articles available.</p>
        <% } %>
    </div>

    <!-- Report Section -->
    <div id="report" class="section">
        <div style="text-align: right; margin-bottom: 20px;">
            
            
            <a href="myReports.jsp" class="write-btn" style="margin-right: 10px;">📄 My Reports</a>
            <a href="writeReport.jsp" class="write-btn">📝 Write Report</a>
        </div>
        <%
            if (reports != null && !reports.isEmpty()) {
                for (ReportBean report : reports) {
        %>
        <div class="card">
            <div class="card-text">
                <h3>🚨 <%= report.getReportTitle() %></h3>
                <p><%= report.getReportDescription() %></p>
            </div>
            <div class="card-meta">
                <p><strong>Reported by:</strong> <%= report.getClientName() %></p>
                <p><strong>Date:</strong> <%= report.getReportDate() %></p>
                <p><strong>Approved by:</strong> <%= report.getAdminName() %></p>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No reports available.</p>
        <% } %>
    </div>

    <!-- Resources Section (Dynamic) -->
    <div id="resources" class="section">
        <%
            if (resources != null && !resources.isEmpty()) {
                for (ResourceBean res : resources) {
        %>
        <div class="card">
            <div class="card-text">
                <h3>🔗 <%= res.getResourceHeader() %></h3>
                <p><%= res.getResourceDescription() %></p>
            </div>
            <div class="card-meta">
                <p><strong>Link:</strong> <a href="#"><%= res.getResourceLink() %></a></p>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No resources available.</p>
        <% } %>
    </div>

    <!-- Contact Section (Dynamic) -->
    <div id="contact" class="section">
        <%
            if (contacts != null && !contacts.isEmpty()) {
                for (ContactBean contact : contacts) {
        %>
        <div class="card">
            <div class="card-text">
                <h3><%= contact.getContactHeader() %></h3>
                <p><%= contact.getContactDescription() %></p>
            </div>
            <div class="card-meta">
                <p><strong>Details:</strong> <%= contact.getContactExtra() %></p>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No contact information available.</p>
        <% } %>
    </div>

</div>

<script>
    function showSection(id, element) {
        document.querySelectorAll('.section').forEach(sec => sec.classList.remove('active'));
        document.getElementById(id).classList.add('active');
        document.querySelectorAll('.navbar a').forEach(link => link.classList.remove('active'));
        if (element) element.classList.add('active');
    }

    // Automatically open section if redirected from servlet
    window.onload = function () {
        const sectionFromServer = '<%= request.getAttribute("showSection") != null ? request.getAttribute("showSection") : "" %>';
        if (sectionFromServer) {
            showSection(sectionFromServer, document.getElementById('nav-' + sectionFromServer));
        }
    };
</script>


</body>
</html>
