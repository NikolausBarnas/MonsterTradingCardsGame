package com.mtcg.repository.user;

import com.mtcg.database.DBConnection;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsDaoImpl;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {
    private Connection connection;
    private static UserDao instance = null;
    PreparedStatement selectCoins = null;
    PreparedStatement updateCoins = null;
    PreparedStatement insert = null;
    PreparedStatement delete = null;
    PreparedStatement update = null;

    static {
        UserDao.instance = new UserDao();
    }

    private UserDao() {
        connection = DBConnection.getInstance().getConnection();
    }

    public static UserDao getInstance() {
        return instance;
    }


    public int getCoins(String username) throws SQLException {
        if (selectCoins == null) {
            try {
                selectCoins = connection.prepareStatement("""
            SELECT coins
            FROM users 
            WHERE username = ?
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            selectCoins.setString(1, username);
            ResultSet resultSet = selectCoins.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("coins");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateCoins(String username, int coins) {
        if (updateCoins == null) {
            try {
                updateCoins = connection.prepareStatement("""
                UPDATE users
                SET coins = ?
                WHERE username = ?
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            updateCoins.setInt(1, coins);
            updateCoins.setString(2, username);
            return updateCoins.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

