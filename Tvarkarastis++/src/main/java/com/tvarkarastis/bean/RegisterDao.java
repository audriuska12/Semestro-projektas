package com.tvarkarastis.bean;

import java.sql.*;

public class RegisterDao {

    public static int register(User u) {
        int status = 0;
        PreparedStatement ps = null;
        try {
            Connection con = ConnectionProvider.getCon();
            ps = con.prepareStatement("insert into users values(default,?,?)");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());

            status = ps.executeUpdate();
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }

        return status;
    }

}