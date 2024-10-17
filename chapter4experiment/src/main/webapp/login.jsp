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
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    // 简单的用户名和密码验证（实际应用中应使用数据库和加密）
    if ("xuzh".equals(username) && "123".equals(password)) {
        HttpSession mySession = request.getSession();
        mySession.setAttribute("username", username);
        response.sendRedirect("welcome.jsp");
    } else {
        System.out.println("用户名或密码错误");
        response.sendRedirect("index.jsp");
    }
%>