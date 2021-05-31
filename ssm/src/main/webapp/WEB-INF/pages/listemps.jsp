<%--
  Created by IntelliJ IDEA.
  User: zhaozhipeng
  Date: 2021/5/31
  Time: 8:15
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*"%>
<%@ page import="com.mezzp.bean.Employee" %>
<html>
<head>
    <title>列表显示</title>
</head>
<body>
<%
    List<Employee> employees=(List<Employee>)request.getAttribute("employees");
    out.println(employees);
%>
</table>
</body>
</html>
