package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.repository.game.BattleExchange;
import com.mtcg.repository.game.BattleLog;
import com.mtcg.repository.user.UserDao;
import com.mtcg.server.Response;

import java.util.Objects;
import java.util.concurrent.SynchronousQueue;

public class BattleHandler implements DirectiveDAOHandler {
    private Gson gson = null;

    private SynchronousQueue<BattleExchange> queue;
    private String token;

    public BattleHandler(String token, SynchronousQueue<BattleExchange> queue) {
        this.gson = new Gson();
        this.token = token;
        this.queue = queue;
    }

    @Override
    public Response handlePost(String body) {
        BattleExchange battleExchange = new BattleExchange(token);
        try {
            queue.put(battleExchange);
        } catch (InterruptedException e) {
            return new Response(500, "");
        }
        BattleLog battleLog = battleExchange.getBattleLog();
        if (battleLog == null) {
            return new Response(500, "");
        }

        int elo = 0;
        elo = UserDao.getInstance().getElo(token);
        if (Objects.equals(battleLog.getWinner(), token)) {
            System.out.println(battleLog.getWinner() + "IF");
            System.out.println(token);
            UserDao.getInstance().updateElo(token,elo + 3);
        } else if(!Objects.equals(battleLog.getWinner(),"draw")) {
            System.out.println(battleLog.getWinner());
            System.out.println(token);
            UserDao.getInstance().updateElo(token, Math.max(elo - 5, 0));
        }
        if (UserDao.getInstance().updateGameCount(token) == -1)
            return new Response(500,"");

        return new Response(200, gson.toJson(battleLog,BattleLog.class));
    }

    @Override
    public Response handleGet(String body) {
        return new Response(200, "");
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
