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
<%
    out.println(UserManagerDao.uninvite(1,1,1));
    out.println(UserManagerDao.uninvite(2,1,1));
    out.println(UserManagerDao.uninvite(1,2,2));
    out.println(UserManagerDao.uninvite(1,2,1));
    out.println(UserManagerDao.uninvite(1,2,1));
%>
</body>
</html>
