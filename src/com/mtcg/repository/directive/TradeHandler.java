package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsDaoImpl;
import com.mtcg.repository.user.UserDao;
import com.mtcg.repository.user.UserMediator;
import com.mtcg.server.Response;

import java.sql.SQLException;
import java.util.Objects;

public class TradeHandler implements DirectiveDAOHandler {
    private Gson gson = null;
    private String token;
    private String addDir;

    public TradeHandler(String token, String addDir) {
        this.gson = new Gson();
        this.token = token;
        this.addDir = addDir;
    }

    @Override
    public Response handlePost(String body) {
        return new Response(500, "");
    }

    @Override
    public Response handleGet(String username) {
        return new Response(200, "");
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
