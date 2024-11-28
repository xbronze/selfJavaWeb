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
<form action="${pageContext.request.contextPath}/ControllerServlet" method="post">
    <h3>用户注册</h3>
    <div id="outer">
        <div>
            <div class="ch">姓名:</div>
            <div class="ip">
                <input type="text" name="name" value="${formBean.name }"/><span>${formBean.errors.name}${DBMes}</span></div>
        </div>
        <div>
            <div class="ch">密码:</div>
            <div class="ip">
                <input type="text" name="password" value="${formBean.password}"> <span>${formBean.errors.password}</span></div>
        </div>
        <div>
            <div class="ch">确认密码:</div>
            <div class="ip">
                <input type="text" name="password2" value="${formBean.password2}"><span>${formBean.errors.password2}</span></div>
        </div>
        <div>
            <div class="ch">邮箱:</div>
            <div class="ip">
                <input type="text" name="email" value="${formBean.email }"><span>${formBean.errors.email}</span></div>
        </div>
        <div id="bt">
            <input type="reset" value="重置"/>
            <input type="submit" value="注册"/></div>
    </div>
</form>
</body>
</html>
