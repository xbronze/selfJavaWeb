<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/9/21
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="text-align: center;">
<form action="${pageContext.request.contextPath}/param.jsp">
  num1：<input type="text" name="num1"><br />
  num2：<input type="text" name="num"><br />
  num3：<input type="text" name="num"><br /> <br />
  <input type="submit" value="提交" />&nbsp;&nbsp;
  <input type="submit" value="重置" /><hr />
  num1： ${param.num1} <br />
  num2： ${paramValues.num[0]} <br />
  num3： ${paramValues.num[1]} <br />
</form>
</body>

</html>
