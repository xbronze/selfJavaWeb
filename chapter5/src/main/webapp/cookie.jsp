<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/9/21
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie对象</title>
</head>
<body>
Cookie对象的信息：<br />
${cookie.userName } <br />
Cookie对象的名称和值：<br />
${cookie.userName.name }=${cookie.userName.value }
<% response.addCookie(new Cookie("userName", "JavaWeb")); %>

<%-- cookie设置的值，不能有空格，否则会报错 --%>
</body>
</html>
