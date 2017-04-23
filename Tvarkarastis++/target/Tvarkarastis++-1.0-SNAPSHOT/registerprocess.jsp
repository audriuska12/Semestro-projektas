<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-20
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@page import="com.tvarkarastis.bean.RegisterDao"%>
<jsp:useBean id="obj" class="com.tvarkarastis.bean.User"/>

<jsp:setProperty property="*" name="obj"/>

<%
    int status=RegisterDao.register(obj);
    if(status>0) {
        out.print("You are successfully registered");
        session.setAttribute("username", obj.getUsername());
        session.removeAttribute("regfailed");
        response.sendRedirect("index.jsp");
    } else {
        session.setAttribute("regfailed", true);
        response.sendRedirect("register.jsp");
    }
%>
