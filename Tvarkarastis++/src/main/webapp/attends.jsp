<%@ page import="com.tvarkarastis.entity.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tvarkarastis.dao.EventManagerDao" %><%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-23
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <%@include file="stylesheetsHeader.jsp"%>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.userProfile"), resources.getString("msg.appName")));%>
    </title>
</head>

<body>
<%@include file="header.jsp"%>
<%@include file="userMenuHeader.jsp"%>
<td class="user-content-container">
    <c:choose>
        <c:when test="${attends != null && attends.size() > 0}">
            <div class="table-and-name-container">
                <div class="table-name-container">
                    <h3 class="table-title">
                        <% out.println(resources.getString("msg.userAttends")); %>
                    </h3>
                </div>
                <div class="table-container">

                    <table class="event-table">
                        <tr class="event-table">
                            <th class="event-table"><% out.println(resources.getString("msg.eventHost")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.eventName")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.eventLocation")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.eventStart")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.eventEnd")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.eventVisibility")); %></th>
                            <th class="event-table"/>
                        </tr>
                        <c:forEach items="${attends}" var="event">
                            <tr class="event-table">
                                <td class="event-table"><c:out value="${event.hostUser.username}"/></td>
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
                                <td class="event-table-time">
                                    <a class="image" href="/unattend/id=${event.id}">
                                        <img class="remove" src="/images/remove.png"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="no-elements">
                <h4 class="no-elements">
                    <%out.println(resources.getString("msg.noAttends"));%>
                </h4>
            </div>
        </c:otherwise>
    </c:choose>


    <c:if test="${unattendSuccess != null}">
        <div class="system-message">
            <c:choose>
                <c:when test="${unattendSuccess == true}">
                    <h4 class="success">
                        <% out.println(resources.getString("msg.inviteWasRejected")); %>
                    </h4>
                </c:when>
                <c:otherwise>
                    <h4 class="failed">
                        <% out.println(resources.getString("msg.errorDuringRequest")); %>
                    </h4>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>


</td>
</table>

<%@include file="footer.jsp"%>

</body>
</html>