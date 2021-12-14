package com.mtcg.repository.card;

import com.mtcg.repository.RepositoryDao;

import java.sql.SQLException;
import java.util.Optional;

public class CardRepositoryImpl implements RepositoryDao<Card> {
    private final CardDaoImpl cardDao;
    private static CardRepositoryImpl instance = null;

    static {
        CardRepositoryImpl.instance = new CardRepositoryImpl();
    }
    private CardRepositoryImpl() { cardDao = CardDaoImpl.getInstance(); }

    @Override
    public void add(Card card) throws SQLException {
        cardDao.create(card);
    }

    @Override
    public Optional<Card> get(Card card) throws SQLException {
        return cardDao.read(card);
    }

    public static CardRepositoryImpl getInstance() {return instance;}
}
