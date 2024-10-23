<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/10/23
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  使用绝对路径构造URL:<br />
  <c:url var="myURL" value="http://localhost:8080/chapter6/c_out.jsp">
    <c:param name="username" value="张三" />
  </c:url>
  <a href="${myURL}">c_out.jsp</a> <br />




  使用相对路径构造URL:<br />
  <c:url var="myURL2"  value="c_out.jsp?username=Tom" />
  <a href="${myURL2}">c_out.jsp</a>

</body>
</html>
