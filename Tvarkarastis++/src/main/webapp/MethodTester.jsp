<%@ page import="com.tvarkarastis.dao.EventManagerDao" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.tvarkarastis.entity.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Time" %>
<%@ page import="com.tvarkarastis.dao.UserManagerDao" %>
<%@ page import="java.sql.Array" %>
<%@ page import="com.tvarkarastis.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tvarkarastis.dao.MessageManagerDao" %>
<%@ page import="com.tvarkarastis.entity.Message" %><%--
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
<table>
<tr>
    <th>Name</th>
    <th>Location</th>
    <th>Start</th>
    <th>End</th>
    <th>Visibility</th>
</tr>
<%
    Event e = EventManagerDao.getEvent(10);
    out.println("<tr>");
    out.println("<td>" + e.getName() + "</td>");
    out.println("<td>" + e.getLocation() + "</td>");
    out.println("<td>" + e.getStart().toString() + "</td>");
    out.println("<td>" + e.getEnd().toString() + "</td>");
    if (e.isPublic()) {
        out.println("<td>Public</td");
    } else {
        out.println("<td>Private</td>");
    }
    out.println("</tr>");
%>
</table>
</body>
</html>
