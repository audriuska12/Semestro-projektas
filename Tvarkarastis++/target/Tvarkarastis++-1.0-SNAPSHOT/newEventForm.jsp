<%--
  Created by IntelliJ IDEA.
  User: Edvinas
  Date: 2017-05-16
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@include file="tagHeader.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
        <div class="form-container">
            <h3 class="form-header">
                <%out.println(resources.getString("msg.createNewEventMessage"));%>
            </h3>
            <form:form action="/addNewEvent" method="POST" commandName="newEvent">
                <table class="form-container">
                    <tr>
                        <td class="form-label">
                            <%out.println(resources.getString("msg.eventName"));%>
                        </td>
                        <td class="form-entry">
                            <form:input class="form-entry" type="text" path="name"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label">
                            <%out.println(resources.getString("msg.eventLocation"));%>
                        </td>
                        <td class="form-entry">
                            <form:input class="form-entry" type="text" path="location"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label">
                            <%out.println(resources.getString("msg.eventStart"));%>
                        </td>
                        <td class="form-entry">
                            <form:input class="form-entry" type="text" path="startDateTime"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label">
                            <%out.println(resources.getString("msg.eventEnd"));%>
                        </td>
                        <td class="form-entry">
                            <form:input class="form-entry" type="text" path="endDateTime"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="form-label">
                            <%out.println(resources.getString("msg.eventVisibility"));%>
                        </td>
                        <td class="form-entry">
                            <form:radiobutton class="form-entry" path="public" value="true" id="public"/>
                            <label for="public" class="form-entry">
                                <%out.println(resources.getString("msg.public"));%>
                            </label>
                            <form:radiobutton class="form-entry" path="public" value="false" id="private" />
                            <label for="private" class="form-entry">
                                <%out.println(resources.getString("msg.private"));%>
                            </label>
                        </td>
                    </tr>
                </table>
                <form:hidden path="host" />
                <c:if test="${insertEventStatus != null && insertEventStatus < 0}">
                    <c:choose>
                        <c:when test="${insertEventStatus == -1}">
                            <h4 class="failed">
                                <%out.println(resources.getString("msg.InvalidNewEventData"));%>
                            </h4>
                        </c:when>
                        <c:otherwise>
                            <h4 class="failed">
                                <%out.println(resources.getString("msg.errorDuringRequest"));%>
                            </h4>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <div class="form-bottom">
                    <input type="submit" value="<%out.println(resources.getString("msg.createNew"));%>"/>
                </div>
            </form:form>
        </div>
    </td>
    </table>
<%@include file="footer.jsp"%>
</body>
</html>

