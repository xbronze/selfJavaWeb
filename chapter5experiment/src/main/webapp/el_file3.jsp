<%@ page import="java.util.Map" %>
<%@ page import="java.util.LinkedHashMap" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/10/31
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>EL表达式隐式对象</title>
  </head>
  <body>
    <%
      Map<String, String> map = new LinkedHashMap<String, String>();
      map.put("a", "aaaaxxx");
      map.put("b", "bbbb");
      map.put("c", "cccc");
      request.setAttribute("map", map);
    %>
    <!-- 根据关键字取map集合的数据 -->
    ${map.c} <br>
    ${map["b"]} <br>
    <!-- JSTL标签 迭代Map集合 -->
    <c:forEach var="me" items="${map}">
      ${me.key}=${me.value}<br/>
    </c:forEach>
  </body>

</html>
