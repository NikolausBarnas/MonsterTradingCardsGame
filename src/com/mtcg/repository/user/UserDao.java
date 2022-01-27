package com.mtcg.repository.user;

import com.mtcg.database.DBConnection;
import com.mtcg.repository.scoreboard.ScoreboardEntry;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;
    private static UserDao instance = null;
    PreparedStatement selectCoins = null;
    PreparedStatement updateCoins = null;
    PreparedStatement getUserData = null;
    PreparedStatement updateUserData = null;
    PreparedStatement getElo = null;
    PreparedStatement getScoreboard = null;
    PreparedStatement updateElo = null;
    PreparedStatement updateGameCount = null;

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

    public int updateCoins(String username, int coins) {
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
            return updateCoins.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public UserMediator getUserData(String username) {
        if (getUserData == null) {
            try {
                getUserData = connection.prepareStatement("""
                    SELECT name,bio,image
                    FROM users
                    WHERE username = ?
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            getUserData.setString(1, username);
            ResultSet resultSet = getUserData.executeQuery();
            if (resultSet.next())
                return new UserMediator(resultSet.getString("name"), resultSet.getString("bio"),
                        resultSet.getString("image"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateUserData(String username, UserMediator userMediator) {
        if (updateUserData == null) {
            try {
                updateUserData = connection.prepareStatement("""
                    UPDATE users 
                    SET name = ?, bio = ?, image = ?
                    WHERE username = ?
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            updateUserData.setString(1, userMediator.getName());
            updateUserData.setString(2, userMediator.getBio());
            updateUserData.setString(3, userMediator.getImage());
            updateUserData.setString(4, username);
            return updateUserData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getElo(String username) {
        if (getElo == null) {
            try {
                getElo = connection.prepareStatement("""
                    SELECT elo
                    FROM users 
                    WHERE username = ?;
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            getElo.setString(1, username);
            ResultSet resultSet = getElo.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("elo");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<ScoreboardEntry> getScoreboard() {
        ArrayList<ScoreboardEntry> scoreboardEntries = new ArrayList<>();
        int rank = 1;
        if (getScoreboard == null) {
            try {
                getScoreboard = connection.prepareStatement("""
                    SELECT username,elo,gamecount
                    FROM users
                    ORDER BY elo
                    DESC
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            ResultSet resultSet = getScoreboard.executeQuery();
            while (resultSet.next()) {
                scoreboardEntries.add(new ScoreboardEntry(rank, resultSet.getString("username"),
                        resultSet.getInt("elo"),resultSet.getInt("gamecount")));
                rank++;
            }
            if (!scoreboardEntries.isEmpty())
                return scoreboardEntries;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateElo(String username, int elo) {
        if (updateElo == null) {
            try {
                updateElo = connection.prepareStatement("""
                UPDATE users
                SET elo = ?
                WHERE username = ?
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            updateElo.setInt(1, elo);
            updateElo.setString(2, username);
            return updateElo.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int updateGameCount(String username) {
        if (updateGameCount == null) {
            try {
                updateGameCount = connection.prepareStatement("""
                UPDATE users
                SET gamecount = gamecount+1
                WHERE username = ?
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            updateGameCount.setString(1, username);
            return updateGameCount.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}

