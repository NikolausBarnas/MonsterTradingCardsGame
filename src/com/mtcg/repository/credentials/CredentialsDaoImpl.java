package com.mtcg.repository.credentials;

import com.mtcg.database.DBConnection;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CredentialsDaoImpl implements CredentialsDao {
    private Connection connection;
    private static CredentialsDaoImpl instance = null;
    private static PreparedStatement preparedStatement;

    static {
        CredentialsDaoImpl.instance = new CredentialsDaoImpl();
    }

    private CredentialsDaoImpl() {
        connection = DBConnection.getInstance().getConnection();
    }
    public static CredentialsDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void create(Credentials credentials) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("""
            INSERT INTO users(username, password)
            VALUES (?,?)
            """);
            preparedStatement.setString(1, credentials.getUsername());
            preparedStatement.setString(2, credentials.getHashPassword());
            preparedStatement.execute();
        } catch (PSQLException p) {

        }
    }

    @Override
    public Optional<Credentials> read(Credentials credentials) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("""
            SELECT username, password
            FROM users
            WHERE username = ?
            """);
            preparedStatement.setString(1, credentials.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of( new Credentials(
                        resultSet.getString(1),
                        resultSet.getString(2)
                ) );
            }

        } catch (PSQLException p) {
        }
        return Optional.empty();
    }
}
