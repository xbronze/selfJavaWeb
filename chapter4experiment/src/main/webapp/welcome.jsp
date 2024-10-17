<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/10/17
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
  HttpSession mySession = request.getSession(false);
  if (mySession == null || mySession.getAttribute("username") == null) {
    response.sendRedirect("index.jsp");
    return;
  }
  String username = (String) mySession.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
  <title>欢迎</title>
</head>
<body>
<h2>欢迎, <%= username %>!</h2>
<p><a href="logout.jsp">注销</a></p>
</body>
</html>
