<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/13
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>User List</title>
</head>
<body>
<h1>User List</h1>
<c:if test="${not empty userList}">
  <table border="1">
    <tr>
      <th>Name</th>
      <th>Age</th>
      <th>Gender</th>
    </tr>
    <c:forEach items="${userList}" var="user">
      <tr>
        <td>${user.name}</td>
        <td>${user.age}</td>
        <td>${user.gender}</td>
      </tr>
    </c:forEach>
  </table>
</c:if>

<h2>Users older than 30</h2>
<c:if test="${not empty userList}">
  <ul>
    <c:forEach items="${userList}" var="user">
      <c:if test="${user.age > 30}">
        <li>${user.name} (${user.age}, ${user.gender})</li>
      </c:if>
    </c:forEach>
  </ul>
</c:if>
</body>
</html>
