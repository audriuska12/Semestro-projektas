package com.tvarkarastis.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            } catch (SQLException ex) {
            }
        }
        return id;
    }
}
