<%@ page import="com.tvarkarastis.entity.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tvarkarastis.dao.EventManagerDao" %><%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-19
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="stylesheetsHeader.jsp"%>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.login"), resources.getString("msg.userProfile")));%>
    </title>
</head>

<body>
<%@include file="header.jsp"%>

<%@include file="hostedBy.jsp"%>
<%--<%@include file="attends.jsp"%>--%>
<%--<%@include file="invitations.jsp"%>--%>
<%--<%@include file="publicEvents.jsp"%>--%>

<%@include file="footer.jsp"%>
</body>
</html>
