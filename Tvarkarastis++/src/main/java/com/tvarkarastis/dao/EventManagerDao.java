package com.tvarkarastis.dao;

import com.tvarkarastis.entity.ConnectionProvider;
import com.tvarkarastis.entity.Event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by audri on 2017-04-22.
 */
public class EventManagerDao {

    public static int insertEvent(Event event) { //grazinimo reiksmes: -2: SQL klaida; -1: neteisingi duomenys; 1: teisinga
        if (!event.validate()) return -1;
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                ps = con.prepareStatement("insert into events (`id`, `name`, `location`, `start`, `end`, `public`, `host`) values (default,?,?,?,?,?,?)");
                ps.setString(1, event.getName());
                ps.setString(2, event.getLocation());
                ps.setString(3, event.getStart().toString());
                ps.setString(4, event.getEnd().toString());
                if (event.isPublic()) {
                    ps.setString(5, "1");
                } else ps.setString(5, "0");
                ps.setString(6, String.valueOf(event.getHost()));
                if (ps.executeUpdate() > 0)
                    success = true;
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        if (success) return 1;
        else return -2;
    }

    public static boolean removeEvent(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                ps = con.prepareStatement("DELETE FROM events WHERE id=?");
                ps.setString(1, String.valueOf(id));
                if (ps.executeUpdate() > 0) {
                    success = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return success;
    }

    public static List<Event> getEventsOfUser(String username) {
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
                    events.add(getEventFromRS(rs));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return events;
    }

    public static List<Event> getEventsUserAttends(String username) {
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
                        events.add(getEventFromRS(rs));
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return events;
    }

    public static List<Event> getPublicEvents(String username) {
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
                    events.add(getEventFromRS(rs));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return events;
    }

    public static List<Event> getInvitedEvents(String username){
        ArrayList<Event> events = new ArrayList<Event>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                int id = UserManagerDao.getUserId(username);
                ps = con.prepareStatement("select * from events, invitations where `invitations`.`event`=`events`.`id` AND `invitations`.`user`=? AND events.id NOT IN (SELECT event as id FROM attends WHERE user=?)");
                ps.setString(1, String.valueOf(id));
                ps.setString(2, String.valueOf(id));
                rs = ps.executeQuery();
                while (rs.next()) {
                    events.add(getEventFromRS(rs));
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return events;

    }

    public static List<Event> getEvents(String username, boolean host, String name, String location, LocalDateTime dateStart, LocalDateTime dateEnd){ //username ir host reikalausiu (host true - paims tik hostinamus ivykius, false - ims visus)
        ArrayList<Event> events = new ArrayList<Event>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                int id = UserManagerDao.getUserId(username);
                String query = "SELECT DISTINCT id, name, location, start, end, `public`, host FROM events";
                if(host){
                    query += " WHERE host=?";
                } else {
                    query += ", attends WHERE (host=? OR (user=? && event=id))";
                }
                if(name != null & !name.isEmpty()){
                    query +=" && name=?";
                }
                if(location != null && !location.isEmpty()){
                    query +=" && location=?";
                }
                if(dateStart != null){
                    query +=" && start >= ?";
                }
                if(dateEnd !=null){
                    query +=" && end <= ?";
                }
                int argc = 0;
                ps=con.prepareStatement(query);
                if(host){
                    ps.setString(++argc, String.valueOf(id));
                } else {
                    ps.setString(++argc, String.valueOf(id));
                    ps.setString(++argc, String.valueOf(id));
                }
                if(name != null & !name.isEmpty()){
                    ps.setString(++argc, name);
                }
                if(location != null && !location.isEmpty()){
                    ps.setString(++argc, location);
                }
                if(dateStart != null){
                    ps.setString(++argc, String.valueOf(dateStart));
                }
                if(dateEnd !=null){
                    ps.setString(++argc, String.valueOf(dateEnd));
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    events.add(getEventFromRS(rs));
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return events;
    }

    private static Event getEventFromRS(ResultSet rs) throws SQLException {
        Event event = new Event();
        event.setName(rs.getString("name"));
        event.setLocation(rs.getString("location"));
        event.setStart(rs.getTimestamp("start").toLocalDateTime());
        event.setEnd((rs.getTimestamp("end").toLocalDateTime()));
        event.setPublic(rs.getBoolean("public"));
        event.setHost(rs.getInt("host"));
        return event;
    }
}
