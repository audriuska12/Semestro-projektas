package com.tvarkarastis.bean;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by audri on 2017-04-22.
 */
public class EventManagerDao {

    public static ArrayList<Event> eventsOfUser(String username) {
        ArrayList<Event> events = new ArrayList<Event>();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                int id = UserManagerDao.getUserId(username);
                ResultSet rs;
                ps = con.prepareStatement("select * from events where host = ?");
                ps.setString(1, String.valueOf(id));
                rs = ps.executeQuery();
                while (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setName(rs.getString("name"));
                    event.setLocation(rs.getString("location"));
                    event.setStart(rs.getTimestamp("start").toLocalDateTime());
                    event.setEnd((rs.getTimestamp("end").toLocalDateTime()));
                    event.setPublic(rs.getBoolean("public"));
                    event.setHost(id);
                    events.add(event);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
            }
        }
        return events;
    }

    public static ArrayList<Event> eventsUserAttends(String username) {
        ArrayList<Event> events = new ArrayList<Event>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                int id = UserManagerDao.getUserId(username);
                ps = con.prepareStatement("select event from attends where user = ?");
                ps.setString(1, String.valueOf(id));
                rs = ps.executeQuery();
                ArrayList<Integer> eventids = new ArrayList<Integer>();
                while (rs.next()) eventids.add(rs.getInt(1));
                ps = con.prepareStatement("select * from events where id = ?");
                for (int i : eventids) {
                    ps.setString(1, String.valueOf(i));
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        Event event = new Event();
                        event.setId(rs.getInt("id"));
                        event.setName(rs.getString("name"));
                        event.setLocation(rs.getString("location"));
                        event.setStart(rs.getTimestamp("start").toLocalDateTime());
                        event.setEnd((rs.getTimestamp("end").toLocalDateTime()));
                        event.setHost(id);
                        events.add(event);
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return events;
    }

    public static ArrayList<Event> publicEvents(String username) {
        ArrayList<Event> events = new ArrayList<Event>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                int id = UserManagerDao.getUserId(username);
                ps = con.prepareStatement("select * from events where public = true AND NOT host = ?");
                ps.setString(1, String.valueOf(id));
                rs = ps.executeQuery();
                while (rs.next()) {
                    Event event = new Event();
                    event.setId(rs.getInt("id"));
                    event.setName(rs.getString("name"));
                    event.setLocation(rs.getString("location"));
                    event.setStart(rs.getTimestamp("start").toLocalDateTime());
                    event.setEnd((rs.getTimestamp("end").toLocalDateTime()));
                    event.setPublic(rs.getBoolean("public"));
                    event.setHost(rs.getInt("host"));
                    events.add(event);
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return events;
    }
}
