<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/10/17
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    HttpSession mySession = request.getSession(false);
    if (mySession != null) {
        session.invalidate();
    }
    response.sendRedirect("index.jsp");
%>
