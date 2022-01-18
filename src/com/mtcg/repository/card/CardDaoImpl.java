package com.mtcg.repository.card;

import com.mtcg.database.DBConnection;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class CardDaoImpl implements CardDao {
    private Connection connection;
    private static CardDaoImpl instance = null;
    private static PreparedStatement preparedStatement;

    static {
        CardDaoImpl.instance = new CardDaoImpl();
    }
    private CardDaoImpl() {
        connection = DBConnection.getInstance().getConnection();
    }
    public static CardDaoImpl getInstance() { return instance; }

    @Override
    public void create(Card card) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("""
            INSERT INTO cards (id,name,damage,element,cardtype)
            VALUES (?,?,?,?,?)
            """);
            //WIESO FUNKTIONIERT card.id??????
            preparedStatement.setString(1,card.getId());
            preparedStatement.setString(2,card.getName());
            preparedStatement.setDouble(3,card.getDamage());
            preparedStatement.setString(4,card.elementType.toString());
            preparedStatement.setString(5,card.cardType.toString());
            preparedStatement.execute();
        } catch (PSQLException p) {

        }
    }

    @Override
    public Optional<Card> read(Card card) throws SQLException {
        try {
            preparedStatement = connection.prepareStatement("""

            """);
        } catch (PSQLException p) {

        }
        return Optional.empty();
    }
}
