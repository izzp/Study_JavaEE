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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".delete").click(function () {
                var href = $(this).attr("href");
                alert(href);
                $("form").attr("action", href).submit();
                return false;
            });

        });
    </script>
</head>
<body class="container">
<form action="" method="post">
    <input type="hidden" name="_method" value="delete">
</form>
<h1>列表显示</h1>
<table class="table table-bordered table-hover table-striped">
    <tr class="success">
        <th>empId</th>
        <th>empName</th>
        <th>gender</th>
        <th>email</th>
        <th>depId</th>
        <th>depName</th>
        <th>删除|修改</th>

    </tr>
    <c:forEach items="${employees}" var="employees">
        <tr>
            <td>${employees.empId}</td>
            <td>${employees.empName}</td>
            <td>${employees.gender}</td>
            <td>${employees.email}</td>
            <td>${employees.department.depId}</td>
            <td>${employees.department.depName}</td>
            <td><a class="btn btn-danger delete" id="deleteemp" href="emp/${employees.empId}" role="button">删除</a> <a
                    class="btn btn-success" href="emp/${employees.empId}" role="button">修改</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a class="btn btn-success" href="toAddEmp" role="button">add new employee</a>

</body>
</html>
