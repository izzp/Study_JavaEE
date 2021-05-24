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
<form action="user/login1" method="post">
    <input type="text" name="username"/>
    <input type="text" name="age"/>
    <input type="submit" value="login"/>
</form>
<a href="user/login1?username=tom&age=20">login1</a>
&nbsp;
<a href="user/login2?username=tom&age=20">login2</a>
&nbsp;
<a href="user/login3">login3:Accept-Encoding</a>
&nbsp;
<a href="user/login4">login4:cookie</a>
&nbsp;
<br>
<br>
<form action="user/login5" method="post">
    uid:<input type="text" name="uid"/>
    username:<input type="text" name="username"/>
    age:<input type="text" name="age"/>
    aid:<input type="text" name="address.aid"/>
    province:<input type="text" name="address.province"/>
    city:<input type="text" name="address.city"/>
    <input type="submit" value="login5"/>
</form>
</body>
</html>
