package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsDao;
import com.mtcg.repository.credentials.CredentialsRepositoryImpl;

import java.sql.SQLException;

public class UserHandler implements DirectiveDAOHandler {
    private Gson gson = null;
    private CredentialsRepositoryImpl credentialsRep = null;

    public UserHandler() {
        this.gson = new Gson();
        this.credentialsRep = new CredentialsRepositoryImpl();
    }

    @Override
    public int handlePost(String body) {
        Credentials credentials = gson.fromJson(body, Credentials.class);
        credentials.setHashPassword(Hash.getInstance().generatePasswordHash(credentials.getPassword()));
        System.out.println(credentials.getHashPassword());
        try {
            credentialsRep.add(credentials);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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

