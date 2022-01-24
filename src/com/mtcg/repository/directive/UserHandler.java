package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsDaoImpl;
import com.mtcg.server.Response;

import java.sql.SQLException;

public class UserHandler implements DirectiveDAOHandler {
    private Gson gson = null;
    private CredentialsDaoImpl credentialsDao = null;

    public UserHandler() {
        this.gson = new Gson();
        this.credentialsDao = CredentialsDaoImpl.getInstance();
    }

    @Override
    public Response handlePost(String body) {
        Credentials credentials = gson.fromJson(body, Credentials.class);
        credentials.setHashPassword(Hash.getInstance().generatePasswordHash(credentials.getPassword()));
        System.out.println(credentials.getHashPassword());
        try {
            credentialsDao.create(credentials);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return new Response(200, "");
    }

    @Override
    public Response handleGet(String username) {
        return new Response(404, "");
    }

    @Override
    public Response handlePut(String body) {
        return new Response(404, "");
    }

    @Override
    public Response handleDelete(String username) {
        return new Response(404, "");
    }


}

