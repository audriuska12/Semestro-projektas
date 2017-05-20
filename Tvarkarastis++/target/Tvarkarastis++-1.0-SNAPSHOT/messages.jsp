<%--
  Created by IntelliJ IDEA.
  User: Edvinas
  Date: 2017-05-20
  Time: 11:44
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

    <c:choose>
        <c:when test="${receivedMessages != null && receivedMessages.size() > 0}">
            <div class="table-and-name-container">
                <div class="table-name-container">
                    <h3 class="table-title">
                        <% out.println(resources.getString("msg.hostedBy")); %>
                    </h3>
                </div>
                <div class="table-container">

                    <table class="event-table">
                        <tr class="event-table">
                            <th class="event-table"><% out.println(resources.getString("msg.messageSender")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.messageText")); %></th>
                            <th class="event-table"/>
                        </tr>
                        <c:forEach items="${receivedMessages}" var="message">
                            <tr class="event-table">
                                <td class="event-table"><c:out value="${message.sender}"/></td>
                                <td class="event-table-wide"><c:out value="${message.text}"/></td>
                                <td class="event-table-time">
                                    <a class="image" href="/deleteMessage/id=${message.id}">
                                        <img class="remove" src="/images/remove.png"/>
                                    </a>
                                    <a class="image" href="/reply/id=${message.sender}">
                                        <img class="remove" src="/images/attend.png"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="add-new">
                        <a class="add-new" href="/createMessage">
                            <% out.println(resources.getString("msg.createMessage")); %>
                        </a>
                    </div>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="no-elements">
                <h4 class="no-elements">
                    <%out.println(resources.getString("msg.noMessages"));%>
                </h4>
                <div class="add-new">
                    <a class="add-new" href="/createMessage">
                        <% out.println(resources.getString("msg.createMessage")); %>
                    </a>
                </div>
                <div>
                    <br/>
                </div>
            </div>
        </c:otherwise>
    </c:choose>

    <div class="system-message">
        <c:choose>
            <c:when test="${messageDeleteSuccess != null}">
                <c:choose>
                    <c:when test="${messageDeleteSuccess}">
                        <h4 class="success">
                            <% out.println(resources.getString("msg.messageWasDeletedSuccessfully")); %>
                        </h4>
                    </c:when>
                    <c:otherwise>
                        <h4 class="failed">
                            <% out.println(resources.getString("msg.cannotDeleteMessage")); %>
                        </h4>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${messageSendStatus != null}">
                <c:choose>
                    <c:when test="t${messageSendStatus == 0}">
                        <h4 class="failed">
                            <% out.println(resources.getString("msg.cannotSendMessage")); %>
                        </h4>
                    </c:when>
                    <c:otherwise>
                        <h4 class="success">
                            <% out.println(resources.getString("msg.messageWasSentSuccessfully")); %>
                        </h4>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
    </div>
</td>
</table>

<%@include file="footer.jsp" %>

</body>
</html>
