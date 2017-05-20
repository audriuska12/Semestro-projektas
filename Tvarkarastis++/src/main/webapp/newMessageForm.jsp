<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Edvinas
  Date: 2017-05-20
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="stylesheetsHeader.jsp"%>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.userProfile"), resources.getString("msg.appName")));%>
    </title>
</head>
<body>
<%@include file="header.jsp" %>
<%@include file="userMenuHeader.jsp" %>
<td class="user-content-container">
    <div class="form-container">
        <h3 class="form-header">
            <%out.println(resources.getString("msg.createNewMessage"));%>
        </h3>
        <form:form action="/sendMessage" method="POST" commandName="newMessage">
        <table class="form-container">
            <tr>
                <td class="form-label-message">
                    <%out.println(resources.getString("msg.recipientUsername"));%>
                </td>
                <td class="form-entry">
                    <form:input class="form-entry" type="text" path="recipient"/>
                </td>
            </tr>
            <tr>
                <td class="form-label-message">
                    <%out.println(resources.getString("msg.messageText"));%>
                </td>
                <td class="form-entry">
                    <form:input class="form-entry" type="text" path="text"/>
                </td>
            </tr>
            <input:hidden path="sender"/>
            <input:hidden path="id" />
        </table>
        <div class="form-bottom">
            <input type="submit" value=<%out.println(resources.getString("msg.sendMessage"));%>">
    </div>
    </form:form>
</div>
</td>
</table>
<%@include file="footer.jsp"%>
</body>
</html>
