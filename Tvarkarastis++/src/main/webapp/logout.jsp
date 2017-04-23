<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-19
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("username");
    response.sendRedirect("index.jsp");
%>
