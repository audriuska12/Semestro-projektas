package com.tvarkarastis.bean;

import java.sql.*;

public class RegisterDao {

    public static int register(User u) {
        int status = 0;
        try {
            if (!ConnectionProvider.isInitialized()) {
                ConnectionProvider.Initialize();
            }
            Connection con = ConnectionProvider.getCon();
//            PreparedStatement unique = con.prepareStatement("select count(*) as usercount from user where username = ?");
//            unique.setString(1, u.getUsername());
//            ResultSet isUnique = unique.executeQuery();
//            isUnique.next();
//            if (isUnique.getInt("usercount") > 0) return 0;
//            PreparedStatement counter = con.prepareStatement("select count(*) as rowcount from user");
//            ResultSet count = counter.executeQuery();
//            count.next();
            PreparedStatement ps = con.prepareStatement("insert into users values(default,?,?)");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());

            status = ps.executeUpdate();
        } catch (Exception e) {
        }

        return status;
    }

}