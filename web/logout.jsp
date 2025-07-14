<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Invalidate the session
    HttpSession s = request.getSession(false);
    if (s != null) {
        s.invalidate();
    }

    // Redirect to login/home page
    response.sendRedirect("index.jsp");
%>
