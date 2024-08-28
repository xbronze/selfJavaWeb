<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/8/28
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/chapter2/RequestParamsServlet " method="post">
    用户名：<input type="text" name="username"><br/>
    密&nbsp;&nbsp;&nbsp;码：<input type="password" name="password"><br/>
    爱好：
    <input type="checkbox" name="hobby" value="sing">唱歌
    <input type="checkbox" name="hobby" value="dance">跳舞
    <input type="checkbox" name="hobby" value="football">Rap<br/>
    <input type="submit" value="提交">
</form>

</body>
</html>
