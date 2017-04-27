<%@ page import="com.tvarkarastis.bean.EventManagerDao" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.tvarkarastis.bean.UserManagerDao" %><%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-27
  Time: 09:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Cia puslapis testuot metodams - galutinej svetainej jo nebus ir geriau nuorodu i ji (ir is jo) nekelinet--%>
<html>
<head>
    <title>Test</title>
</head>
<body>
<%
    if(session.getAttribute("username") == null) session.setAttribute("username", "testuser5");
    Event evInsert = new Event();
    evInsert.setName("inserttest");
    evInsert.setLocation("inserttest");
    evInsert.setStart(LocalDateTime.now());
    evInsert.setEnd(LocalDateTime.now());
    evInsert.setPublic(true);
    evInsert.setHost(UserManagerDao.getUserId(session.getAttribute("username").toString()));
    out.println(EventManagerDao.InsertEvent(evInsert));
%>
<%@include file ="hostedBy.jsp"%>
</body>
</html>
