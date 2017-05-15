package com.tvarkarastis.dao;

import com.tvarkarastis.entity.ConnectionProvider;
import com.tvarkarastis.entity.User;

import java.sql.*;

public class RegisterDao {

    public static int register(User u) { // -4: jau yra toks user; -3: netinkamas slaptazodis; -2: netinkamas email; -1: netinkamas username; 0: kazkas nepavyko 1: sekmingai ideta
        int valid = u.validate();
        if(valid<0) return valid;
        if (UserManagerDao.getUserId(u.getUsername()) != -1) return -4;
        int status = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConnectionProvider.getCon();
            if (con != null) {
                ps = con.prepareStatement("insert into users values(default,?,?,?)");
                ps.setString(1, u.getEmail());
                ps.setString(2, u.getUsername());
                ps.setString(3, u.getPassword());
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