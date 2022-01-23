package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsRepositoryImpl;
import com.mtcg.repository.session.SessionDao;
import com.mtcg.repository.session.SessionDaoImpl;

import java.sql.SQLException;
import java.util.Objects;

public class SessionHandler implements DirectiveDAOHandler{

    private Gson gson = null;
    private CredentialsRepositoryImpl credentialsRepository = null;
    private Credentials credentials = null;
    private SessionDaoImpl sessionDao = null;

    public SessionHandler() {
        this.gson = new Gson();
        this.credentialsRepository = new CredentialsRepositoryImpl();
        this.sessionDao = new SessionDaoImpl();
    }

    @Override
    public int handlePost(String body) throws SQLException {

        Hash hash = Hash.getInstance();
        try {
            credentials = gson.fromJson(body, Credentials.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        if (credentialsRepository.get(credentials).isPresent()) {
            if ((hash.checkPassword(credentialsRepository.get(credentials).get().getPassword(), credentials.getPassword()))) {
                if (Objects.equals(sessionDao.getSession(credentials), ""))
                    sessionDao.createSession(credentials);
                else
                    return 406;
            } else {
                return 406;
            }
        }

        return 200;
    }

    @Override
    public String handleGet(String username) {
        return null;
    }

    @Override
    public int handlePut(String body) {
        return 400;
    }

    @Override
    public int handleDelete(String username) {
        return 400;
    }
}
