<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/9/21
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  用户名：<%=request.getAttribute("username")%><br/>
  密码：<%=request.getAttribute("password")%><br/>
  <hr/>
  使用EL表达式获取用户名和密码<br/>
  用户名：${username}
  密码：${password}
</body>
</html>
