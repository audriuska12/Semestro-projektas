package com.tvarkarastis.bean;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Created by audri on 2017-04-22.
 */
public class EventLister {

    public static ArrayList<Event> eventsOfUser(String username) {
        ArrayList<Event> events = new ArrayList<Event>();
        try {

            Connection con = ConnectionProvider.getCon();
            PreparedStatement user = con.prepareStatement("select id from users where username = ?");
            user.setString(1, username);
            ResultSet rs = user.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            PreparedStatement ps = con.prepareStatement("select * from events where host = ?");
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
        } catch (Exception e) {
            String err = e.toString();
        }
        return events;
    }

    public static ArrayList<Event> eventsUserAttends(String username) {
        ArrayList<Event> events = new ArrayList<Event>();
        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement user = con.prepareStatement("select id from users where username = ?");
            user.setString(1, username);
            ResultSet rs = user.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            PreparedStatement ps1 = con.prepareStatement("select event from attends where user = ?");
            ps1.setString(1, String.valueOf(id));
            rs = ps1.executeQuery();
            ArrayList<Integer> eventids = new ArrayList<Integer>();
            while(rs.next()) eventids.add(rs.getInt(1));
            PreparedStatement ps2 = con.prepareStatement("select * from events where id = ?");
            for(int i: eventids){
                ps2.setString(1, String.valueOf(i));
                rs = ps2.executeQuery();
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
        } catch (Exception e) {
        }
        return events;
    }

    public static ArrayList<Event> publicEvents(String username) {
        ArrayList<Event> events = new ArrayList<Event>();
        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement user = con.prepareStatement("select id from users where username = ?");
            user.setString(1, username);
            ResultSet rs = user.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            PreparedStatement ps = con.prepareStatement("select * from events where public = true AND NOT host = ?");
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
        } catch (Exception e) {
        }
        return events;
    }
}
