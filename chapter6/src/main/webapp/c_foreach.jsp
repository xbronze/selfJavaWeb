<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/10/23
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  String数组中的元素：<br/>
  <% String[] fruits = {"apple", "orange", "grape", "banana"}; %>

  <c:forEach var="item" items="<%=fruits%>" varStatus="status">
    当前元素索引值：${status.index} &nbsp; 当前元素序号：${status.count} &nbsp; 当前元素的值：${item} <br/>
  </c:forEach>


  <%
    Map userMap = new HashMap();
    userMap.put("Tom", "123");
    userMap.put("Make", "123");
    userMap.put("Lina", "123");
  %>
  HashMap集合中的元素：<br/>
  <c:forEach var="entry" items="<%=userMap%>">
    ${entry.key}&nbsp;${entry.value}<br />
  </c:forEach>

</body>
</html>
