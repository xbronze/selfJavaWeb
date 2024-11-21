<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/20
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% if (session.getAttribute("userBean") == null) { %>
<jsp:forward page="register.jsp"/>
<% return;
} %>
<div id="main">
    <div id="welcome">恭喜你，登录成功</div>
    <hr/>
    <div>您的信息</div>
    <div>
        <ul>
            <li>您的姓名:${userBean.name }</li>
            <li>您的邮箱:${userBean.email }</li>
        </ul>
    </div>
</div>

</body>
</html>
