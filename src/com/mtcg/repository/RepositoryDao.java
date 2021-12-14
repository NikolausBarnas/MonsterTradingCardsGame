package com.mtcg.repository;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Optional;

public interface RepositoryDao<T> {
    Optional<T> get(T item) throws SQLException;
    void add(T item) throws SQLException;
}
