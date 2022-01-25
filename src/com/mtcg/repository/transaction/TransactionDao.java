package com.mtcg.repository.transaction;

import com.mtcg.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class TransactionDao {

    private Connection connection;
    private static TransactionDao instance = null;
    PreparedStatement update = null;

    static {
        TransactionDao.instance = new TransactionDao();
    }

    private TransactionDao() { connection = DBConnection.getInstance().getConnection();
    }
    public static TransactionDao getInstance() { return instance; }

    public Optional<String> read(String s) {
        return Optional.empty();
    }

    public int update(String s, String owner) {
        if (update == null) {
            try {
                update = connection.prepareStatement("""
                UPDATE cards 
                SET owner = ?
                WHERE id = ?    
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            update.setString(1,owner);
            update.setString(2,s);
            return update.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

}
