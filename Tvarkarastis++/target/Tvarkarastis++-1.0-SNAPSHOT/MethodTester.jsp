<%@ page import="com.tvarkarastis.dao.EventManagerDao" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="com.tvarkarastis.entity.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Time" %>
<%@ page import="com.tvarkarastis.dao.UserManagerDao" %>
<%@ page import="java.sql.Array" %>
<%@ page import="com.tvarkarastis.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tvarkarastis.dao.MessageManagerDao" %>
<%@ page import="com.tvarkarastis.entity.Message" %><%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-27
  Time: 09:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Cia puslapis testuot metodams - galutinej svetainej jo nebus ir geriau nuorodu i ji (ir is jo) nekelinet--%>
<html>
<head>
    <title>Test</title>
</head>
<body>
Message #2:
<table>
    <tr>
    <th>Recipient</th>
    <th>Sender</th>
    <th>Text</th>
    </tr>
<%
    Message mess = MessageManagerDao.getMessage(1, 2);
    out.println("<tr>");
    out.println("<td>" + mess.getRecipient().getUsername()+ "</td>");
    out.println("<td>" + mess.getSender().getUsername()+ "</td>");
    out.println("<td>" + mess.getText()+ "</td>");
    out.println("</tr>");
%>
</table>
Sent messages:
<table>
    <tr>
        <th>Recipient</th>
        <th>Sender</th>
        <th>Text</th>
    </tr>
    <%
        List<Message> messages = MessageManagerDao.messagesSent(1);
        for(Message msg1: messages){
            out.println("<tr>");
            out.println("<td>" + msg1.getRecipient().getUsername()+ "</td>");
            out.println("<td>" + msg1.getSender().getUsername()+ "</td>");
            out.println("<td>" + msg1.getText()+ "</td>");
            out.println("</tr>");
        }
    %>
</table>
Received messages for user 2:
<table>
    <tr>
        <th>Recipient</th>
        <th>Sender</th>
        <th>Text</th>
    </tr>
    <%
        List<Message> messages2 = MessageManagerDao.messagesReceived(2);
        for(Message msg2: messages2){
            out.println("<tr>");
            out.println("<td>" + msg2.getRecipient().getUsername()+ "</td>");
            out.println("<td>" + msg2.getSender().getUsername()+ "</td>");
            out.println("<td>" + msg2.getText()+ "</td>");
            out.println("</tr>");
        }
    %>
</table>
Received messages for user 3:
<table>
    <tr>
        <th>Recipient</th>
        <th>Sender</th>
        <th>Text</th>
    </tr>
    <%
        List<Message> messages3 = MessageManagerDao.messagesReceived(2);
        for(Message msg3: messages3){
            out.println("<tr>");
            out.println("<td>" + msg3.getRecipient().getUsername()+ "</td>");
            out.println("<td>" + msg3.getSender().getUsername()+ "</td>");
            out.println("<td>" + msg3.getText()+ "</td>");
            out.println("</tr>");
        }
    %>
</table>
Received messages for user 4:
<table>
    <tr>
        <th>Recipient</th>
        <th>Sender</th>
        <th>Text</th>
    </tr>
    <%
        List<Message> messages4 = MessageManagerDao.messagesReceived(4);
        for(Message msg4: messages4){
            out.println("<tr>");
            out.println("<td>" + msg4.getRecipient().getUsername()+ "</td>");
            out.println("<td>" + msg4.getSender().getUsername()+ "</td>");
            out.println("<td>" + msg4.getText()+ "</td>");
            out.println("</tr>");
        }
    %>
</table>
</body>
<%
    MessageManagerDao.deleteMessage(1);
%>
</html>
