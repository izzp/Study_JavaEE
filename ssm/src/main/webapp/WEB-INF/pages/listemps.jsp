<%--
  Created by IntelliJ IDEA.
  User: zhaozhipeng
  Date: 2021/5/31
  Time: 8:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>列表显示</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.4.1/css/bootstrap.min.css"/>
</head>
<body class="container">
<h1>列表显示</h1>
<table class="table table-bordered table-hover table-striped">
    <tr class="success">
        <th>empId</th>
        <th>empName</th>
        <th>gender</th>
        <th>email</th>
        <th>depId</th>
        <th>depName</th>

    </tr>
    <c:forEach items="${employees}" var="employees">
        <tr>
            <td>${employees.empId}</td>
            <td>${employees.empName}</td>
            <td>${employees.gender}</td>
            <td>${employees.email}</td>
            <td>${employees.department.depId}</td>
            <td>${employees.department.depName}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
