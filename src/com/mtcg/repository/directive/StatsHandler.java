package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.repository.user.UserDao;
import com.mtcg.server.Response;


public class StatsHandler implements DirectiveDAOHandler{
    private Gson gson = null;
    private String token;

    public StatsHandler(String token) {
        this.gson = new Gson();
        this.token = token;
    }

    @Override
    public Response handlePost(String body) {
        return new Response(404, "");
    }

    @Override
    public Response handleGet(String body) {
        int elo = UserDao.getInstance().getElo(token);
        String json = "{\"elo\":"+ elo + "}";
        if (elo == -1)
            return new Response(500, "ELO NOT FOUND");
        return new Response(200, json);
    }

    @Override
    public Response handlePut(String body) {
        return new Response(404, "");
    }

    @Override
    public Response handleDelete(String body) {
        return new Response(404, "");
    }

}
