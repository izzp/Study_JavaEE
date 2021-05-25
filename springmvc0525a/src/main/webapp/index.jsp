<%--
  Created by IntelliJ IDEA.
  User: zhaozhipeng
  Date: 2021/5/25
  Time: 8:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主页</title>
    <style type="text/css">
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<form action="user/update" method="post">
    <input type="hidden" name="id" value="1001"/>
    <input type="text" name="username" value="Tom"/>
    <input type="text" name="age" value="23"/>
    <input type="submit" value="update"/>
</form>
</body>
</html>
