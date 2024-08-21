package org.platzi.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/project";

    private static String user = "rootUser";

    private static String password = "toor12345";

    private static Connection myConn = null;

    public static Connection getInstance() throws SQLException {
        if (myConn == null) {
            myConn = DriverManager.getConnection(url, user, password);
        }
        return myConn;
    }

}
