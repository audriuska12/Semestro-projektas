<%@ taglib prefix="form" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="tagHeader.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: Edvinas
  Date: 2017-05-18
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <%@include file="stylesheetsHeader.jsp" %>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.userProfile"), resources.getString("msg.appName")));%>
    </title>
</head>

<body>

<%@include file="header.jsp" %>
<%@include file="userMenuHeader.jsp" %>
<td class="user-content-container">

    <div class="table-and-name-container">
        <div class="form-container">
            <h3 class="form-header">
                <% out.println(resources.getString("msg.inviteToEvent")); %>
            </h3>
        </div>
        <div class="table-container">

            <table class="event-table">
                <tr class="event-table">
                    <th class="event-table"><% out.println(resources.getString("msg.eventName")); %></th>
                    <th class="event-table"><% out.println(resources.getString("msg.eventLocation")); %></th>
                    <th class="event-table"><% out.println(resources.getString("msg.eventStart")); %></th>
                    <th class="event-table"><% out.println(resources.getString("msg.eventEnd")); %></th>
                    <th class="event-table"><% out.println(resources.getString("msg.eventVisibility")); %></th>
                    <th class="event-table"/>
                </tr>
                <tr class="event-table">
                    <td class="event-table"><c:out value="${event.name}"/></td>
                    <td class="event-table"><c:out value="${event.location}"/></td>
                    <td class="event-table-time"><c:out value="${event.start}"/></td>
                    <td class="event-table-time"><c:out value="${event.end}"/></td>
                    <td class="event-table">
                        <c:choose>
                            <c:when test="${event.open}">
                                <%out.println(resources.getString("msg.public"));%>
                            </c:when>
                            <c:otherwise>
                                <%out.println(resources.getString("msg.private"));%>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
        </div>

        <form:form action="/invite" method="POST" commandName="userToInvite">
            <table class="form-container">
                <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.username"));%>
                    </td>
                    <td class="form-label">
                        <form:input class="form-entry" type="text" path="username"/>
                    </td>
                </tr>
            </table>

            <%--Reikia validavimo--%>

            <div class="form-bottom">
                <input type="submit" value="<%out.println(resources.getString("msg.invite"));%>"/>
            </div>
        </form:form>
    </div>

</td>
</table>

<%@include file="footer.jsp" %>

</body>
</html>