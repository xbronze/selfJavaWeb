<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/10/23
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <c:if test="${empty param.username}">
        This param username is empty
    </c:if>


</body>
</html>
