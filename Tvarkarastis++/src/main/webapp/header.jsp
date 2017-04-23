<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-22
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if(session.getAttribute("username") != null){
        out.println(String.format("Welcome, %s</br>", session.getAttribute("username")));
        out.println("<a href=\"profile.jsp\">profile</a>|");
        out.println("<a href=\"logout.jsp\">logout</a>|");
    } else {
        out.println("<a href=\"login.jsp\">login</a>|");
        out.println("<a href=\"register.jsp\">register</a>|");
    }
%>
</br>
