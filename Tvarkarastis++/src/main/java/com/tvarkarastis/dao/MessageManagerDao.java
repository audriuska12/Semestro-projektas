package com.tvarkarastis.dao;

import com.tvarkarastis.entity.ConnectionProvider;
import com.tvarkarastis.entity.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by audri on 2017-05-09.
 */
public class MessageManagerDao {

    public static Message getMessage(int userId, int messageId){
        Connection con = null;
        PreparedStatement ps = null;
        Message message = null;
        try{
            con = ConnectionProvider.getCon();
            if(con != null){
                ps = con.prepareStatement("SELECT messageheader.id, sender, recipient, messagetext.text FROM messageheader, messagetext WHERE (sender = ? OR recipient = ?) AND messageheader.id = ? AND messageheader.text = messagetext.id ");
                ps.setString(1, String.valueOf(userId));
                ps.setString(2, String.valueOf(userId));
                ps.setString(3, String.valueOf(messageId));
                ResultSet rs = ps.executeQuery();
                rs.next();
                message = getMessageFromRS(rs);
            }
        }catch(Exception e){}finally{
            try{
                ps.close();
                con.close();
            } catch (Exception e){
            }
        }
        return message;
    }

    public static List<Message> getMessagesSent(int userId){
        ArrayList<Message> messages = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = ConnectionProvider.getCon();
            if(con != null) {
                ps = con.prepareStatement("SELECT messageheader.id, sender, recipient, messagetext.text FROM messageheader, messagetext WHERE sender = ?  AND messageheader.text = messagetext.id ");
                ps.setString(1, String.valueOf(userId));
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    messages.add(getMessageFromRS(rs));
                }
            }
        }catch(Exception e){}finally{
            try{
                ps.close();
                con.close();
            } catch (Exception e){
            }
        }
        return messages;
    }

    public static List<Message> getMessagesReceived(int userId){
        ArrayList<Message> messages = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = ConnectionProvider.getCon();
            if(con != null) {
                ps = con.prepareStatement("SELECT messageheader.id, sender, recipient, messagetext.text FROM messageheader, messagetext WHERE recipient = ?  AND messageheader.text = messagetext.id ");
                ps.setString(1, String.valueOf(userId));
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    messages.add(getMessageFromRS(rs));
                }
            }
        }catch(Exception e){}finally{
            try{
                ps.close();
                con.close();
            } catch (Exception e){
            }
        }
        return messages;
    }

    public static int send(int senderID, String text, int... recipientIDs) {
        return sendMessage(senderID, recipientIDs, text);
    }

    public static int sendMessage(int senderId, int[] recipientIds, String messageText){ //grazina sekmingai issiustu skaiciu
        Connection con = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            con = ConnectionProvider.getCon();
            if(con != null) {
                ps = con.prepareStatement("INSERT INTO messagetext (id, text) VALUES (default, ?)", Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, messageText);
                ps.executeUpdate();
                ResultSet idSet = ps.getGeneratedKeys();
                idSet.next();
                int textId = idSet.getInt(1);
                ps = con.prepareStatement("INSERT INTO messageheader (id, sender, recipient, text) VALUES (default, ?,?,?)");
                ps.setString(1, String.valueOf(senderId));
                ps.setString(3, String.valueOf(textId));
                for(int rec : recipientIds){
                    ps.setString(2, String.valueOf(rec));
                    count += ps.executeUpdate();
                }
            }
        }catch(Exception e){}finally{
            try{
                ps.close();
                con.close();
            } catch (Exception e){
            }
        }
        return count;
    }

    public static boolean deleteMessage(int messageId){
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;
        try{
            con = ConnectionProvider.getCon();
            if(con != null){
                ps = con.prepareStatement("DELETE FROM messageheader WHERE id = ?");
                ps.setString(1, String.valueOf(messageId));
                if(ps.executeUpdate() > 0){
                    success = true;
                    ps = con.prepareStatement("DELETE FROM messagetext WHERE id NOT IN (SELECT text FROM messageheader)");
                    ps.executeUpdate();
                }
            }
        }catch(Exception e){} finally {
            try{ps.close();
            con.close();}catch(Exception e){}
        }
        return success;
    }

    private static Message getMessageFromRS(ResultSet rs) throws SQLException {
        Message message = new Message();
        message.setId(rs.getInt("id"));
        message.setSender(UserManagerDao.getUser(rs.getInt("sender")));
        message.setRecipient(UserManagerDao.getUser(rs.getInt("recipient")));
        message.setText(rs.getString("text"));
        return message;
    }
//    private static Message getMessageFromRS(ResultSet rs) throws SQLException {
//        Message message = new Message();
//        message.setId(rs.getInt("id"));
//        message.setSender(UserManagerDao.getUser(rs.getInt("sender")).getId());
//        message.setRecipient(UserManagerDao.getUser(rs.getInt("recipient")).getId());
//        message.setText(rs.getString("text"));
//        return message;
//    }
}
