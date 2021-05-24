<%--
  Created by IntelliJ IDEA.
  User: zhaozhipeng
  Date: 2021/5/24
  Time: 8:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
</head>
<body>
<form action="order" method="post">
    <input type="hidden" name="_method" value="put"/>
    <input type="submit" value="update"/>
</form>
<form action="order/1001" method="post">
    <input type="hidden" name="_method" value="delete"/>
    <input type="submit" value="delete"/>
</form>
<br>
<a href="order/1001">get order</a>
<a href="hello">hello</a>
</body>
</html>
