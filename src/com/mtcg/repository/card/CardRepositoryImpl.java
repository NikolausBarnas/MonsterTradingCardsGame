package com.mtcg.repository.card;

import com.mtcg.repository.RepositoryDao;

import java.sql.SQLException;
import java.util.Optional;

public class CardRepositoryImpl implements RepositoryDao<Card> {
    private CardDaoImpl cardDao = null;

    public CardRepositoryImpl() { this.cardDao = new CardDaoImpl(); }

    @Override
    public void add(Card card) throws SQLException {
        cardDao.create(card);
    }

    @Override
    public Optional<Card> get(Card card) throws SQLException {
        return cardDao.read(card);
    }

}
