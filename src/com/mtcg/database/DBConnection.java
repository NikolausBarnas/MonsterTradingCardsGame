package com.mtcg.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static DBConnection instance = null;

    static {
        DBConnection.instance = new DBConnection();
    }
    private DBConnection () {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/swe1user", "swe1user", "swe1pw");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
    public static DBConnection getInstance() {
        return instance;
    }
}
