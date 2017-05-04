<%@ page import="com.tvarkarastis.bean.EventManagerDao" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.tvarkarastis.bean.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Time" %>
<%@ page import="com.tvarkarastis.bean.UserManagerDao" %>
<%@ page import="java.sql.Array" %>
<%@ page import="com.tvarkarastis.bean.User" %><%--
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
    <th>Username</th>
<%
    if(session.getAttribute("username") == null) session.setAttribute("username", "testuser");
    ArrayList<User> users = UserManagerDao.Friends(1);
    for(User u: users){
        out.println("<tr>" + u.getUsername() + "</tr>");
    }
    users = UserManagerDao.Friends(2);
    for(User u: users){
        out.println("<tr>" + u.getUsername() + "</tr>");
    }
%>
</table>
</body>
</html>
