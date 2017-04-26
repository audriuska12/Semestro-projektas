package com.tvarkarastis.bean;

/**
 * Created by audri on 2017-04-16.
 */

import java.sql.*;

import static com.tvarkarastis.bean.Provider.*;

public class ConnectionProvider {

    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName(DRIVER).newInstance();
            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
        }
        return con;
    }

}
