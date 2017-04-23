<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-16
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<hr/>

<h3>Login</h3>

<%
    if(session.getAttribute("logfailed") != null && (boolean)session.getAttribute("logfailed")){
    out.println("Login failed </br>");
    session.removeAttribute("logfailed");
    }
%>
<form action="loginprocess.jsp" method="post">
    Username:<input type="text" name="username"/><br/><br/>
    Password:<input type="password" name="password"/><br/><br/>
    <input type="submit" value="login"/>"
</form>
