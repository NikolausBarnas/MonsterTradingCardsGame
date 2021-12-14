package com.mtcg.repository.credentials;

import java.sql.SQLException;
import java.util.Optional;

public interface CredentialsDao {
    void create(Credentials credentials) throws SQLException;
    Optional<Credentials> read(Credentials credentials) throws SQLException;
    //void update(User user);
    //void delete(String userName);
}
