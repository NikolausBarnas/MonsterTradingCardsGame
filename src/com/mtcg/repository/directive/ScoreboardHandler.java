package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.repository.scoreboard.ScoreboardEntry;
import com.mtcg.repository.user.UserDao;
import com.mtcg.server.Response;

import java.util.ArrayList;


public class ScoreboardHandler implements DirectiveDAOHandler{
    private Gson gson = null;
    private String token;

    public ScoreboardHandler(String token) {
        this.gson = new Gson();
        this.token = token;
    }

    @Override
    public Response handlePost(String body) {
        return new Response(404, "");
    }

    @Override
    public Response handleGet(String username) {
        ArrayList<ScoreboardEntry> scoreboardEntries = UserDao.getInstance().getScoreboard();
        if (scoreboardEntries == null) {
            return new Response(500, "");
        }
        return new Response(200, gson.toJson(scoreboardEntries.toArray(),ScoreboardEntry[].class));
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
