package com.mtcg.repository;

import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.directive.DirectiveDAOHandler;
import com.mtcg.repository.directive.DirectiveHandler;
import com.mtcg.repository.session.SessionDaoImpl;
import com.mtcg.server.HttpRequest;
import com.mtcg.server.Response;

import java.sql.SQLException;

public class RequestHandler {

    DirectiveDAOHandler directiveDAOHandler;

    public Response handleRequest(HttpRequest req) throws SQLException {

        if (req.getToken() != null) {
            if (SessionDaoImpl.getInstance().getSession(req.getToken()) == "") {
                return new Response(401, "");
            }
        }

        DirectiveHandler dirHandler = new DirectiveHandler();
        directiveDAOHandler = dirHandler.getDirectiveObject(req.getDirective(), req.getToken());
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
