<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-20
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@include file="header.jsp"%>
<%
    if(session.getAttribute("regfailed") != null && (boolean)session.getAttribute("regfailed")){
        out.println("Username already in use </br>");
        session.removeAttribute("regfailed");
    }
%>
<form action="registerprocess.jsp" method="post">
    Email:<input type = "text" name = "email"/><br/><br/>
    Username:<input type="text" name="username"/><br/><br/>
    Password:<input type="password" name="password"/><br/><br/>
    <input type="submit" value="register"/>"
</form>
</body>
</html>
