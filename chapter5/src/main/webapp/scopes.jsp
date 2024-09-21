<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/9/21
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EL隐式对象中Web域相关对象</title>
</head>
<body>
<%--使用pageScope、requestScope、sessionScope和applicationScope这四个隐式对象成功的获取到了相应JSP域对象中的属性值。--%>
<%--需要注意的是，使用EL获取某个域对象中的属性时，也可以不使用这些隐式对象指定查找域，而直接引用域中的属性名称。--%>
<%--例如，表达式${userName}就是在page、request、session、application这4个作用域内按顺序依次查找userName属性。--%>

<% pageContext.setAttribute("userName", "Java EE"); %>
<% request.setAttribute("bookName", "Java Web"); %>
<% session.setAttribute("userName", "Python"); %>
<% application.setAttribute("bookName", "Go"); %>
表达式\${pageScope.userName}的值为：${pageScope.userName} <br />
表达式\${requestScope.bookName}的值为：${requestScope.bookName} <br />
表达式\${sessionScope.userName}的值为：${sessionScope.userName} <br />
表达式\${applicationScope.bookName}的值为：${applicationScope.bookName} <br />
表达式\${userName}的值为：${userName}

</body>
</html>
