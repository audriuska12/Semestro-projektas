package com.tvarkarastis.entity;

/**
 * Created by audri on 2017-04-16.
 */

import java.sql.*;

public class ConnectionProvider {

    static String DRIVER = "com.mysql.jdbc.Driver";
    static String CONNECTION_URL = "jdbc:mysql://localhost:3306/tvarkarastis";
    static String USERNAME = "java";
    static String PASSWORD = "javaadmin";

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
