<%--
  Created by IntelliJ IDEA.
  User: zhaozhipeng
  Date: 2021/5/25
  Time: 8:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" isELIgnored="false" %>
<html>
<head>
    <title>成功</title>
</head>
<body>
<h1>success</h1>
handle2 --> username:<%=request.getAttribute("username")%><br/>
handle2 --> username:${requestScope.username} <br/>
handle2 --> age:${requestScope.age} <hr/>
handle3 --> username:${requestScope.username} --> 简写方式找到的 --> ${username}<br/>
handle3 --> password:${password} <br/>

handle3 --> sessionScope --> username:${sessionScope.username} <br/>
handle3 --> sessionScope --> password:${sessionScope.password}
<hr>
handle4 --> password:${user} <br/>

</body>
</html>
