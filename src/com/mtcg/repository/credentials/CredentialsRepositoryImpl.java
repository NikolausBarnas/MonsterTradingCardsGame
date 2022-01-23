package com.mtcg.repository.credentials;

import com.mtcg.repository.RepositoryDao;

import java.sql.SQLException;
import java.util.Optional;

public class CredentialsRepositoryImpl implements RepositoryDao<Credentials> {
    private final CredentialsDaoImpl credentialsDaoImpl;

    public CredentialsRepositoryImpl() {
        credentialsDaoImpl = new CredentialsDaoImpl();
    }

    @Override
    public void add(Credentials credentials) throws SQLException {
        credentialsDaoImpl.create(credentials);
    }

    @Override
    public Optional<Credentials> get(Credentials credentials) throws SQLException {
        return credentialsDaoImpl.read(credentials);
    }
}
