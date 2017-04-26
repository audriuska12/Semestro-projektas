<%@ page import="com.tvarkarastis.bean.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tvarkarastis.bean.EventManagerDao" %><%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-23
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
Public:
<table>
    <tr>
        <th>Name</th>
        <th>Location</th>
        <th>Start</th>
        <th>End</th>
        <th>Visibility</th>
    </tr>
    <%
        ArrayList<Event> eventsPublic = EventManagerDao.publicEvents(session.getAttribute("username").toString());
        for (Event e : eventsPublic) {
            out.println("<tr>");
            out.println("<td>" + e.getName() + "</td>");
            out.println("<td>" + e.getLocation() + "</td>");
            out.println("<td>" + e.getStart().toString() + "</td>");
            out.println("<td>" + e.getEnd().toString() + "</td>");
            if (e.isPublic()) {
                out.println("<td>Public</td");
            } else {
                out.println("<td>Private</td>");
            }
            out.println("</tr>");
        }
    %>
</table>