package com.mtcg.repository;

import com.mtcg.repository.directive.DirectiveDAOHandler;
import com.mtcg.repository.directive.DirectiveHandler;
import com.mtcg.repository.game.BattleExchange;
import com.mtcg.repository.session.SessionDao;
import com.mtcg.server.HttpRequest;
import com.mtcg.server.Response;

import java.sql.SQLException;
import java.util.concurrent.SynchronousQueue;

public class RequestHandler {

    private DirectiveDAOHandler directiveDAOHandler;

    private SynchronousQueue<BattleExchange> queue;

    public RequestHandler(SynchronousQueue<BattleExchange> queue) {
        this.queue = queue;
    }

    public Response handleRequest(HttpRequest req) throws SQLException {

        if (req.getToken() != null) {
            if (SessionDao.getInstance().getSession(req.getToken()) == "") {
                return new Response(401, "");
            }
        }

        DirectiveHandler dirHandler = new DirectiveHandler();
        directiveDAOHandler = dirHandler.getDirectiveObject(req.getDirective(), req.getToken(), queue);
        Response response = chooseDirective(req);
        return response;
    }

    public Response chooseDirective(HttpRequest req) throws SQLException {
        switch(req.getMethod()) {
            case "POST" -> {
                return directiveDAOHandler.handlePost(req.getBody());
            }
            case "GET" -> {
                return directiveDAOHandler.handleGet(req.getBody());
            }
            case "PUT" -> {
                return directiveDAOHandler.handlePut(req.getBody());
            }
            case "DELETE" -> {
                return directiveDAOHandler.handleDelete(req.getBody());
            }
        }
        return null;
    }

}
