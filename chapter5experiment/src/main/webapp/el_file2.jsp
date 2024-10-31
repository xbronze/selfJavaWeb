<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/10/31
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>EL表达式运算符</title>
    </head>
    <body>
        <h3>EL表达式进行四则运算</h3>
        ${(1 + 2) * 3} <br>
        ${10 / 2} <br>
        ${10 % 3} <br>
        ${(1 == 2) ? 'true' : 'false'} <br>
        ${(1 > 2) ? 'true' : 'false'} <br>
    </body>

</html>
