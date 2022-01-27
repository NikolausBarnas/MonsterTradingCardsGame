package com.mtcg.repository.session;

import com.mtcg.database.DBConnection;
import com.mtcg.repository.credentials.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDao {

    private Connection connection;
    private static SessionDao instance = null;

    static {
        SessionDao.instance = new SessionDao();
    }

    private SessionDao() {
        connection = DBConnection.getInstance().getConnection();
    }

    public static SessionDao getInstance() {
        return instance;
    }

    public boolean createSession (Credentials credentials) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
            INSERT INTO sessions (username)
            VALUES (?)
        """);
            preparedStatement.setString(1, credentials.getUsername());
            return preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String getSession(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
            SELECT username 
            FROM sessions
            WHERE username = ?
        """);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }
}
