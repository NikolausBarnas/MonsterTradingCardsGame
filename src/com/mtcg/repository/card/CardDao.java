package com.mtcg.repository.card;

import com.mtcg.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDao {
    private Connection connection;
    private static CardDao instance = null;
    PreparedStatement selectOwner = null;
    PreparedStatement insert = null;
    PreparedStatement updateDeck = null;
    PreparedStatement selectAll = null;
    PreparedStatement selectDeckPlain = null;
    PreparedStatement selectDeck = null;

    static {
        CardDao.instance = new CardDao();
    }
    private CardDao() {
        connection = DBConnection.getInstance().getConnection();
    }
    public static CardDao getInstance() { return instance; }

    public int create(Card card) {

        if (insert == null) {

            try {
                insert = connection.prepareStatement("""
            INSERT INTO cards (id,name,damage,element,cardtype,package_id)
            VALUES (?,?,?,?,?,?)
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            insert.setString(1,card.getId());
            insert.setString(2,card.getName());
            insert.setDouble(3,card.getDamage());
            insert.setString(4,card.elementType.toString());
            insert.setString(5,card.cardType.toString());
            insert.setInt(6,card.packageID);
            return insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getOwner(String cardID) {
        if (selectOwner == null) {
            try {
                selectOwner = connection.prepareStatement("""
                SELECT owner 
                FROM cards
                WHERE id = ?
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            selectOwner.setString(1, cardID);
            ResultSet resultSet = selectOwner.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("owner");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public ArrayList<CardMediator> getAll(String username) {

        ArrayList<CardMediator> cards = new ArrayList<>();
        if (selectAll == null) {
            try {
                selectAll = connection.prepareStatement("""
                SELECT * FROM cards
                WHERE owner = ?
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            selectAll.setString(1, username);
            ResultSet resultSet = selectAll.executeQuery();
            while (resultSet.next()) {
                cards.add(new CardMediator(resultSet.getString("id"), resultSet.getString("name"),
                        resultSet.getInt("damage")));
            }
            return cards;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<String> getDeckPlain (String owner) {
        ArrayList<String> deck = new ArrayList<>();
        if (selectDeckPlain == null) {
            try {
                selectDeckPlain = connection.prepareStatement("""
                SELECT id FROM cards
                WHERE owner = ?
                AND isInDeck = TRUE
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            selectDeckPlain.setString(1, owner);
            ResultSet resultSet = selectDeckPlain.executeQuery();
            while (resultSet.next()) {
                deck.add(resultSet.getString("id"));
            }
            return deck;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updateDeck(String cardID, String owner) {
        if (updateDeck == null) {
            try {
                updateDeck = connection.prepareStatement("""
                UPDATE cards
                SET isindeck = TRUE
                WHERE id = ?
                AND owner = ?
                """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            updateDeck.setString(1, cardID);
            updateDeck.setString(2, owner);
            updateDeck.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 200;
    }

    public ArrayList<CardMediator> getDeck (String owner) {
        ArrayList<CardMediator> deck = new ArrayList<>();
        if (selectDeck == null) {
            try {
                selectDeck = connection.prepareStatement("""
                SELECT id, name, damage
                FROM cards
                WHERE owner = ?
                AND isindeck = true
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            selectDeck.setString(1, owner);
            ResultSet resultSet = selectDeck.executeQuery();
            while (resultSet.next()) {
                deck.add(new CardMediator(resultSet.getString("id"), resultSet.getString("name"),
                        resultSet.getInt("damage")));
            }
            return deck;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
