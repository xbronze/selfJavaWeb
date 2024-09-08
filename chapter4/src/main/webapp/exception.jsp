<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/9/8
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>exception object test</title>
</head>
<body>
  <%
    int a = 3;
    int b = 0;
  %>
  输出结果为：<%=(a / b)%><!--此处会产生异常  -->
</body>

</html>
