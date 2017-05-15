<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-22
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    out.println("<div class=\"container\">");

    out.println("<div class=\"header\">");
    out.println(String.format("<h1><a class=\"home\" href=\"index.jsp\">%s</a></h1>", resources.getString("msg.appName")));
    out.println("</div>");

    out.println("<div class=\"user-info\">");
    if(session.getAttribute("username") != null){

        out.println("<ul class=\"menu\">");
        out.println(String.format("<li class=\"user-info-left-align\">%s %s</li>", resources.getString("msg.welcome"), session.getAttribute("username")));
        out.println(String.format("<li class=\"menu-elem-right-align\"><a href=\"logout.jsp\">%s</a></li>", resources.getString("msg.logout")));
        out.println(String.format("<li class=\"menu-elem-right-align\"><a href=\"profile.jsp\">%s</a></li>", resources.getString("msg.profile")));
        out.println("</ul>");

    } else {

        out.println("<ul class=\"menu\">");
        out.println(String.format("<li class=\"menu-elem-left-align\"><a href=\"login.jsp\">%s</a></li>", resources.getString("msg.login")));
        out.println(String.format("<li class=\"menu-elem-left-align\"><a href=\"register.jsp\">%s</a></li>", resources.getString("msg.register")));
        out.println("</ul>");
    }

    out.println("</div>");

    out.println("<div class=\"content\">");
%>
<%--</br>--%>