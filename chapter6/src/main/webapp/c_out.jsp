<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>

<c:out value="${param.username }" escapeXml="true">
  <meta http-equiv="refresh" content="0;url=http://www.baidu.com" />
</c:out>

</body>
</html>