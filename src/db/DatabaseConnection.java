package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "fp510";
    private static final String PASSWORD = "510";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}


