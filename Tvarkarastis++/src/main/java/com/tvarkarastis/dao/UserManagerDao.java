package com.tvarkarastis.dao;

import com.tvarkarastis.entity.ConnectionProvider;
import com.tvarkarastis.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by audri on 2017-04-26.
 */
public class UserManagerDao {

    public static int getUserId(String username) {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement user = null;
        int id = -1;
        try {
            user = con.prepareStatement("select id from users where username = ?");
            user.setString(1, username);
            ResultSet rs = user.executeQuery();
            rs.next();
            id = rs.getInt("id");
        } catch (Exception e) {
        } finally {
            try {
                user.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return id;
    }

    public static User getUser(int id) {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        User user = null;
        try {
            ps = con.prepareStatement("select * from users where id = ?");
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = getUserFromRS(rs);
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return user;
    }

    public static int invite(int inviterId, int invitedId, int eventId){ //-3: kviecia ne host'as; -2: nera tokio event'o; -1: kviecia save; 0: SQL klaida (greiciausiai jau pakviestas) 1: pavyko
        if(inviterId==invitedId) return -1;
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        int success = 0;
        try{
            ps = con.prepareStatement("SELECT host FROM events WHERE id = ?");
            ps.setString(1, String.valueOf(eventId));
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) success = -2;
            if(inviterId != rs.getInt("host")){
                success = -3;
            } else {
                ps = con.prepareStatement("INSERT INTO invitations(user, event) VALUES (?, ?)");
                ps.setString(1, String.valueOf(invitedId));
                ps.setString(2, String.valueOf(eventId));
                success = ps.executeUpdate();
            }
        } catch(Exception e){
            String err = e.toString();
            System.out.println(err);
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
            }
        }
        return success;
    }

    public static int uninvite(int uninviterId, int uninvitedId, int eventId){ // -3: ne host'as -2: ner event'o -1: pats save 0: nebuvo pakviestas 1: pavyko
        if(uninviterId==uninvitedId) return -1;
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        int success = 0;
        try{
            ps = con.prepareStatement("SELECT host FROM events WHERE id = ?");
            ps.setString(1, String.valueOf(eventId));
            ResultSet rs = ps.executeQuery();
            if(!rs.next()) success = -2;
            if(uninviterId != rs.getInt("host")){
                success = -3;
            } else {
                ps = con.prepareStatement("DELETE FROM invitations WHERE user = ? AND event = ?");
                ps.setString(1, String.valueOf(uninvitedId));
                ps.setString(2, String.valueOf(eventId));
                success = ps.executeUpdate();
            }
        } catch(Exception e){
            String err = e.toString();
            System.out.println(err);
        } finally{
            try{
                ps.close();
                con.close();
            } catch(Exception e){
            }
        }
        return success;
    }

    public static int attend(int eventId, int userId) { //-1: nekviestas; -2: SQL klaida; -3: toks jau yra; 1: suveike
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        int success = 0;
        try {
            ps = con.prepareStatement("SELECT `public` from `events` where id=?");
            ps.setString(1, String.valueOf(eventId));
            ResultSet rs = ps.executeQuery();
            rs.next();
            boolean isPublic = rs.getBoolean(1);
            if (!isPublic) {
                if (!isInvited(eventId, userId)) {
                    return -1;
                }
            }
            ps = con.prepareStatement("INSERT INTO `attends` (`event`, `user`) VALUES (?, ?)");
            ps.setString(1, String.valueOf(eventId));
            ps.setString(2, String.valueOf(userId));
            success = ps.executeUpdate();
        } catch (Exception e) {
            success = -2;
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                return -2;
            }
        }
        if (success > 0) return 1;
        else return -3;
    }

    public static boolean unattend(int eventId, int userId) {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        int success = 0;
        if (con != null) {
            try {
                ps = con.prepareStatement("DELETE FROM attends WHERE event=? && user=?");
                ps.setString(1, String.valueOf(eventId));
                ps.setString(2, String.valueOf(userId));
                success = ps.executeUpdate();
            } catch (Exception e) {
            } finally {
                try {
                    ps.close();
                    con.close();
                } catch (Exception e) {
                }
            }
        }
        return success > 0;
    }

    public static int follow(int thisId, int otherId) {// -3: pats su savim -2: SQL klaida (greiciausiai jau yra) -1: ner tokio userio 1: pavyko
        if (thisId == otherId) return -3;
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        int success = 0;
        if (con != null) {
            try {
                ps = con.prepareStatement("INSERT INTO follows (this, other) VALUES (?,?)");
                ps.setString(1, String.valueOf(thisId));
                ps.setString(2, String.valueOf(otherId));
                success = ps.executeUpdate();
            } catch (Exception e) {
                return -1;
            } finally {
                try {
                    ps.close();
                    con.close();
                } catch (Exception e) {
                }
            }
        }
        return (success > 0 ? 1 : -1);
    }

    public static boolean unfollow(int thisId, int otherId) {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        int success = 0;
        if (con != null) {
            try {
                ps = con.prepareStatement("DELETE FROM follows WHERE this=? && other=?");
                ps.setString(1, String.valueOf(thisId));
                ps.setString(2, String.valueOf(otherId));
                success = ps.executeUpdate();
            } catch (Exception e) {
            } finally {
                try {
                    ps.close();
                    con.close();
                } catch (Exception e) {
                }
            }
        }
        return success > 0;
    }

    public static List<User> getFollowed(int id) {
        ArrayList users = new ArrayList();
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from users where id IN (SELECT other as id FROM follows WHERE this=?)");
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(getUserFromRS(rs));
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return users;
    }

    public static List<User> getFollowers(int id) {
        ArrayList users = new ArrayList();
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from users where id IN (SELECT this as id FROM follows WHERE other=?)");
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(getUserFromRS(rs));
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return users;
    }

    public static List<User> getFriends(int id) {
        ArrayList users = new ArrayList();
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT others.id, others.username FROM users AS this LEFT JOIN follows ON this.id=follows.this LEFT JOIN users AS others ON others.id=follows.other WHERE this.id=?");
            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                users.add(getUserFromRS(rs));
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return users;
    }

    public static boolean isInvited(int eventId, int userId) {
        Connection con = ConnectionProvider.getCon();
        PreparedStatement ps = null;
        boolean result = false;
        try {
            ps = con.prepareStatement("SELECT COUNT AS `count` FROM `invitations` WHERE `event`=? && `user`=?");
            ps.setString(1, String.valueOf(eventId));
            ps.setString(2, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();
            rs.next();
            result = (rs.getInt("count") > 0);
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
            }
        }
        return result;
    }

    private static User getUserFromRS(ResultSet rs) throws SQLException { //įtariu, kad geriau nedaryt metodo, kuris masiskai rankios slaptazodzius, tai grazineja tik username ir id
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
