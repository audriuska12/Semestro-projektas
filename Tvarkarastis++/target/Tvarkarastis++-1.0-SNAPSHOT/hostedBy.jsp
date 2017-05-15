<%@ page import="com.tvarkarastis.entity.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tvarkarastis.dao.EventManagerDao" %><%--
  Created by IntelliJ IDEA.
  User: audri
  Date: 2017-04-23
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="table-container">
    <div class="table-name-container">
        <h3 class="table-title">
            <% out.println(resources.getString("msg.hostedBy")); %>
        </h3>
    </div>
    <table>
        <tr class="event-table">
            <th class="event-header">Name</th>
            <th class="event-header">Location</th>
            <th class="event-header">Start</th>
            <th class="event-header">End</th>
            <th class="event-header">Visibility</th>
        </tr>
        <%
            List<Event> eventsHosted = EventManagerDao.eventsOfUser(session.getAttribute("username").toString());
            for (Event e : eventsHosted) {
                out.println("<tr>");
                out.println("<td>" + e.getName() + "</td>");
                out.println("<td>" + e.getLocation() + "</td>");
                out.println("<td>" + e.getStart().toString() + "</td>");
                out.println("<td>" + e.getEnd().toString() + "</td>");
                if (e.isPublic()) {
                    out.println("<td>Public</td>");
                } else {
                    out.println("<td>Private</td>");
                }
                out.println("</tr>");
            }
        %>
    </table>
</div>