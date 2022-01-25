package com.mtcg.repository.packages;

import com.mtcg.database.DBConnection;

import java.sql.*;
import java.util.Optional;

public class PackageDao {
    private Connection connection;
    private static PackageDao instance = null;
    PreparedStatement select = null;
    PreparedStatement insert = null;
    PreparedStatement delete = null;
    PreparedStatement update = null;

    static {
        PackageDao.instance = new PackageDao();
    }
    private PackageDao(){ connection = DBConnection.getInstance().getConnection(); }
    public static PackageDao getInstance() { return instance; }

    public int create(Package p) {
        if (insert == null) {
            try {
                insert = connection.prepareStatement("""
                INSERT INTO packages(card1,card2,card3,card4,card5)
                VALUES(?,?,?,?,?)
                RETURNING id
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            insert.setString(1,p.getCards().get(0));
            insert.setString(2,p.getCards().get(1));
            insert.setString(3,p.getCards().get(2));
            insert.setString(4,p.getCards().get(3));
            insert.setString(5,p.getCards().get(4));
            ResultSet resultSet = insert.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch(SQLException e) {
            return -1;
        }
        return -1;
    }

    public Optional<Package> read() {
        if (select == null) {
            try {
                select = connection.prepareStatement("""
                SELECT * FROM packages 
                WHERE id = (SELECT MIN(id) FROM packages)
                """);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new Package(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public int delete(Package p) {
        if (delete == null) {
            try {
                delete = connection.prepareStatement("""
                DELETE FROM packages
                WHERE id = (SELECT MIN(id) FROM packages) 
                """);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            delete.execute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return 200;
    }

}

