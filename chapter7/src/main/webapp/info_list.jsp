<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="com.dingli.chapter7.DatabaseConnect" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/11/21
  Time: 8:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String sql = "select * from user";
        PreparedStatement pstmt = DatabaseConnect.getConnection().prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
    %>
<table style="border: 1px">
    <thead>
        <tr><td>用户id</td><td>用户名</td><td>用户等级</td></tr>
    </thead>
    <tbody>
    <%
        while (rs.next()) {
            int userId = rs.getInt(1);
            String userName = rs.getString(2);
            int userLevel = rs.getInt(3);

    %>

    <tr>
        <td><%=userId%></td>
        <td><%=userName%></td>
        <td><%=userLevel%></td>
    </tr>
    <%
        }
        DatabaseConnect.closeConnection();
    %>
    </tbody>
</table>
</body>
</html>
