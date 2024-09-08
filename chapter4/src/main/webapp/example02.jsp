<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/9/4
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <%!
    public int print() {
      int a=1, b=2;
      return a+b;
    }
  %>
  <br/>
  <%
    out.println(print());
  %>
</body>
</html>
