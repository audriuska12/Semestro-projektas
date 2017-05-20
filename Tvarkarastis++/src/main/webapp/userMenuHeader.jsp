﻿<%--
  Created by IntelliJ IDEA.
  User: Edvinas
  Date: 2017-05-15
  Time: 14:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="user-menu-container">
    <td  class="user-menu-container">
        <div class="user-menu-container">
            <ul class="user-menu" >
                <li class="user-menu">
                    <a class="user-menu" href="/hostedBy">
                        <%out.println(String.format("%s", resources.getString("msg.viewHostedEvents")));%>
                    </a>
                </li>
                <li class="user-menu">
                    <a class="user-menu" href="/attends">
                        <%out.println(String.format("%s", resources.getString("msg.viewAttends")));%>
                    </a>
                </li>
                <li class="user-menu">
                    <a class="user-menu" href="/invitations">
                        <%out.println(String.format("%s", resources.getString("msg.viewInvitations")));%>
                    </a>
                </li>
                <li class="user-menu">
                    <a class="user-menu" href="/messages">
                        <%out.println(String.format("%s", resources.getString("msg.viewMessages")));%>
                    </a>
                </li>
            </ul>
        </div>
    </td>