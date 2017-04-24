package com.tvarkarastis.bean;

/**
 * Created by audri on 2017-04-16.
 */

import java.sql.*;

import static com.tvarkarastis.bean.Provider.*;

public class ConnectionProvider {
    private static Connection con = null;
    private static boolean isInitialized = false;

    public static boolean isInitialized() {
        return isInitialized;
    }

    public static void Initialize() {
        if (isInitialized) {
            return;
        }
        try {
            Class.forName(DRIVER).newInstance();
            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
            isInitialized = true;
        } catch (Exception e) {
            String err = e.toString();
            System.out.println(err);
        }
    }

    public static Connection getCon() {
        return con;
    }

}
