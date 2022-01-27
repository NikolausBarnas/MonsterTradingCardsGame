package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsDao;
import com.mtcg.repository.session.SessionDao;
import com.mtcg.server.Response;

import java.sql.SQLException;
import java.util.Objects;

public class SessionHandler implements DirectiveDAOHandler{

    private Gson gson = null;
    private CredentialsDao credentialsDao = null;
    private Credentials credentials = null;
    private SessionDao sessionDao = null;

    public SessionHandler() {
        this.gson = new Gson();
        this.credentialsDao = CredentialsDao.getInstance();
        this.sessionDao = SessionDao.getInstance();
    }

    @Override
    public Response handlePost(String body) throws SQLException {

        Hash hash = Hash.getInstance();
        try {
            credentials = gson.fromJson(body, Credentials.class);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }

        if (credentialsDao.read(credentials).isPresent()) {
            if ((hash.checkPassword(credentialsDao.read(credentials).get().getPassword(), credentials.getPassword()))) {
                if (Objects.equals(sessionDao.getSession(credentials.getUsername()), ""))
                    sessionDao.createSession(credentials);
                else
                    return new Response(406, "");
            } else {
                return new Response(406, "");
            }
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
