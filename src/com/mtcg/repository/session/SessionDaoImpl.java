package com.mtcg.repository.session;

import com.mtcg.database.DBConnection;
import com.mtcg.repository.credentials.Credentials;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDaoImpl implements SessionDao {

    private static SessionDaoImpl instance = null;
    private Connection connection;

    private SessionDaoImpl() {
        connection = DBConnection.getInstance().getConnection();
    }
    static {
        SessionDaoImpl.instance = new SessionDaoImpl();
    }
    public static SessionDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void createSession (Credentials credentials) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
            INSERT INTO sessions (username)
            VALUES (?)
        """);
            preparedStatement.setString(1, credentials.getUsername());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public String getSession(Credentials credentials) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
            SELECT username 
            FROM sessions
            WHERE username = ?
        """);
            preparedStatement.setString(1, credentials.getUsername());
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
