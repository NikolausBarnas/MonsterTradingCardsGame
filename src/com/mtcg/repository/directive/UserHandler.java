package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsDao;
import com.mtcg.repository.user.UserDao;
import com.mtcg.repository.user.UserMediator;
import com.mtcg.server.Response;

import java.sql.SQLException;
import java.util.Objects;

public class UserHandler implements DirectiveDAOHandler {
    private Gson gson = null;
    private CredentialsDao credentialsDao = null;
    private String token;
    private String addDir;

    public UserHandler(String token, String addDir) {
        this.gson = new Gson();
        this.credentialsDao = CredentialsDao.getInstance();
        this.token = token;
        this.addDir = addDir;
    }

    @Override
    public Response handlePost(String body) {
        Credentials credentials = gson.fromJson(body, Credentials.class);
        credentials.setHashPassword(Hash.getInstance().generatePasswordHash(credentials.getPassword()));
        try {
            if (credentialsDao.create(credentials) == 1)
                return new Response(200, "");
            return new Response(409,"");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Response(500, "");
    }

    @Override
    public Response handleGet(String username) {
        if (!checkIfUserMatch())
            return new Response(401,"");

        UserMediator userMediator = UserDao.getInstance().getUserData(token);
        if (userMediator == null)
            return new Response(500, "");
        return new Response(200, gson.toJson(userMediator, UserMediator.class));
    }

    @Override
    public Response handlePut(String body) {
        if (!checkIfUserMatch())
            return new Response(401,"");

        if (UserDao.getInstance().updateUserData(token, gson.fromJson(body, UserMediator.class)) == 1) {
            return new Response(200, "");
        }
        return new Response(500, "");
    }

    @Override
    public Response handleDelete(String username) {
        return new Response(404, "");
    }

    private boolean checkIfUserMatch() {
        if (!Objects.equals(token, addDir)) {
            return false;
        }
        return true;
    }



}

