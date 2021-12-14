package com.mtcg.repository.card;

import java.sql.SQLException;
import java.util.Optional;

public interface CardDao {
    void create(Card card) throws SQLException;
    Optional<Card> read(Card card) throws SQLException;
}
