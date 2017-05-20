<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-22
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="header">
        <h1>
            <a class="home" href="/">
                <%out.println(resources.getString("msg.appName")); %>
            </a>
        </h1>
    </div>

    <div class="user-info">
        <ul class="menu">
            <c:choose>
                <c:when test="${username != null}">
                    <li class="user-info-left-align">
                        <%out.println(String.format("%s %s", resources.getString("msg.welcome"), session.getAttribute("username")));%>
                    </li>
                    <li class="menu-elem-right-align">
                        <a class="menu-item" href="/logout">
                            <%out.println(resources.getString("msg.logout"));%>
                        </a>
                    </li>
                    <li class="menu-elem-right-align">
                        <a class="menu-item" href="/profile">
                            <%out.println(resources.getString("msg.profile"));%>
                        </a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="menu-elem-left-align">
                        <a class="menu-item" href="/login">
                            <%out.println(resources.getString("msg.login"));%>
                        </a>
                    </li>
                    <li class="menu-elem-left-align">
                        <a class="menu-item" href="/register">
                            <%out.println(resources.getString("msg.register"));%>
                        </a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
<div class="content">