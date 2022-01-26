package com.mtcg.repository.TradingMediator;

import com.mtcg.database.DBConnection;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.session.SessionDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TradeDao {
    private Connection connection;
    private static TradeDao instance = null;
    PreparedStatement select = null;
    PreparedStatement update = null;
    PreparedStatement delete = null;
    PreparedStatement insert = null;
    PreparedStatement getOwner = null;
    PreparedStatement getTrading = null;

    static {
        TradeDao.instance = new TradeDao();
    }

    private TradeDao() {
        connection = DBConnection.getInstance().getConnection();
    }

    public static TradeDao getInstance() {
        return instance;
    }

    public int createTradeOffer (String offeror, TradingMediator tradingMediator) {
        if (insert == null) {
            try {
                insert = connection.prepareStatement("""
                INSERT INTO trading (id,cardtotrade,cardtype,minimumdamage,offeror)
                VALUES (?,?,?,?,?)
                """);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            insert.setString(1,tradingMediator.getId());
            insert.setString(2,tradingMediator.getCardToTrade());
            insert.setString(3,tradingMediator.getType());
            insert.setInt(4,tradingMediator.getMinimumDamage());
            insert.setString(5,offeror);
            return insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public ArrayList<TradingMediator> getTradings(String username) {
        ArrayList<TradingMediator> tradings = new ArrayList<>();
        if (select == null) {
            try {
                select = connection.prepareStatement("""
                SELECT * FROM trading
                WHERE offeror != ?
                """);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            select.setString(1, username);
            ResultSet resultSet = select.executeQuery();
            while (resultSet.next()) {
                tradings.add(new TradingMediator(resultSet.getString("id"),resultSet.getString("cardtotrade"),
                        resultSet.getString("cardtype"),resultSet.getInt("minimumdamage")));
            }
            return tradings;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TradingMediator getTrading(String tradingID) {
        if (getTrading == null) {
            try {
                getTrading = connection.prepareStatement("""
                SELECT * FROM trading
                WHERE id = ?
                """);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            getTrading.setString(1, tradingID);
            ResultSet resultSet = getTrading.executeQuery();
            if (resultSet.next()) {
                return new TradingMediator(resultSet.getString("id"),resultSet.getString("cardtotrade"),
                        resultSet.getString("cardtype"),resultSet.getInt("minimumdamage"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int deleteTrading(String cardID) {
        if (delete == null) {
            try {
                delete = connection.prepareStatement("""
                DELETE FROM trading
                WHERE id = ?
                """);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            delete.setString(1,cardID);
            return delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getOwner(String tradingID) {
        if (getOwner == null) {
            try {
                getOwner = connection.prepareStatement("""
                SELECT offeror 
                FROM trading
                WHERE id = ?
                """);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            getOwner.setString(1, tradingID);
            ResultSet resultSet = getOwner.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("offeror");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}
