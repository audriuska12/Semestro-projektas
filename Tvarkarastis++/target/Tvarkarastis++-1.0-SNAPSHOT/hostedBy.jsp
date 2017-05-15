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
    <table class="event-table">
        <tr class="event-table">
            <th class="event-table">Name</th>
            <th class="event-table">Location</th>
            <th class="event-table">Start</th>
            <th class="event-table">End</th>
            <th class="event-table">Visibility</th>
        </tr>
        <%
            List<Event> eventsHosted = EventManagerDao.eventsOfUser(session.getAttribute("username").toString());
            for (Event e : eventsHosted) {
                out.println("<tr class=\"event-table\">");
                out.println("<td class=\"event-table\">" + e.getName() + "</td>");
                out.println("<td class=\"event-table\">" + e.getLocation() + "</td>");
                out.println("<td class=\"event-table-time\">" + e.getStart().toString() + "</td>");
                out.println("<td class=\"event-table-time\">" + e.getEnd().toString() + "</td>");
                if (e.isPublic()) {
                    out.println("<td class=\"event-table\">Publiccccccc</td>");
                } else {
                    out.println("<td class=\"event-table\">Private</td>");
                }
                out.println("</tr>");
            }
        %>
    </table>
</div>