package com.tvarkarastis.dao;

/**
 * Created by audri on 2017-04-16.
 */

import com.tvarkarastis.entity.ConnectionProvider;
import com.tvarkarastis.entity.LoginBean;

import java.sql.*;

public class LoginDao {

    public static boolean validate(LoginBean bean) {
        boolean status = false;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = ConnectionProvider.getCon();

            ps = con.prepareStatement(
                    "select * from users where username=? and password=?");

            ps.setString(1, bean.getUsername());
            ps.setString(2, bean.getPassword());

            ResultSet rs = ps.executeQuery();
            status = rs.next();
        } catch (Exception e) {
        } finally {
            try {
                ps.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }
}
