package com.mtcg.repository.card;

import com.mtcg.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDao {
    private Connection connection;
    private static CardDao instance = null;
    PreparedStatement select = null;
    PreparedStatement insert = null;
    PreparedStatement updateDeck = null;
    PreparedStatement selectAll = null;
    PreparedStatement selectDeck = null;

    static {
        CardDao.instance = new CardDao();
    }
    private CardDao() {
        connection = DBConnection.getInstance().getConnection();
    }
    public static CardDao getInstance() { return instance; }

    public void create(Card card) {

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
            insert.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Card> read(String username) {
        if (select == null) {
            try {
                select = connection.prepareStatement("""
          
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
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
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getDeck (String owner) {
        ArrayList<String> deck = new ArrayList<>();
        if (selectDeck == null) {
            try {
                selectDeck = connection.prepareStatement("""
                SELECT id FROM cards
                WHERE owner = ?
                AND isInDeck = TRUE
            """);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            selectDeck.setString(1, owner);
            ResultSet resultSet = selectAll.executeQuery();
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

}
