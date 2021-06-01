<%--
  Created by IntelliJ IDEA.
  User: zhaozhipeng
  Date: 2021/6/1
  Time: 8:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>添加emp</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.4.1/css/bootstrap.min.css"/>
</head>
<body class="container">
<h1>添加或修改员工</h1>
<from:form action="${pageContext.request.contextPath}/emp" method="post"  modelAttribute="employee" cssClass="form-horizontal">
    <c:if test="${!empty employee.empId }">
        <form:hidden path="empId" />
        <input type="hidden" name="_method" value="put"/>
    </c:if>
    <div class="form-group">
        <label class="col-sm-2 control-label">empName:</label>
        <div class="col-sm-4">
            <from:input path="empName" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">gender:</label>
        <div class="col-sm-4">
            <form:radiobuttons path="gender" items="${genders}" cssClass="radio-inline"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">email:</label>
        <div class="col-sm-4">
            <from:input path="email" class="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">depId:</label>
        <div class="col-sm-4">
            <form:select path="department.depId" items="${departments}" itemValue="depId" itemLabel="depName"
                         class="form-control" cssClass="form-control"/>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success">添加|修改</button>
        </div>
    </div>
</from:form>


</body>
</html>
