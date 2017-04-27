package com.tvarkarastis.bean;

import java.sql.*;

public class RegisterDao {

    public static int register(User u) {
        if (UserManagerDao.getUserId(u.getUsername()) != -1) return 0;
        int status = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                ps = con.prepareStatement("insert into users values(default,?,?)");
                ps.setString(1, u.getUsername());
                ps.setString(2, u.getPassword());
                status = ps.executeUpdate();
            }
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
            }
        }

        return status;
    }

}