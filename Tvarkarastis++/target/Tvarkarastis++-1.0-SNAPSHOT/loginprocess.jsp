<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-16
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.tvarkarastis.dao.LoginDao"%>
<jsp:useBean id="obj" class="com.tvarkarastis.entity.LoginBean"/>

<jsp:setProperty property="*" name="obj"/>

<%
    boolean status=LoginDao.validate(obj);
    if(status){
        session.setAttribute("username",obj.getUsername());
        session.removeAttribute("logfailed");
        response.sendRedirect("index.jsp");
    }
    else
    {
        session.setAttribute("logfailed", true);
        response.sendRedirect("login.jsp");
    }
%>