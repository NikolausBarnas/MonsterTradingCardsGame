package com.mtcg.repository;

import com.mtcg.repository.directive.DirectiveDAOHandler;
import com.mtcg.repository.directive.DirectiveHandler;
import com.mtcg.server.HttpRequest;

import java.sql.SQLException;

public class RequestHandler {

    DirectiveDAOHandler directiveDAOHandler;


    public void handleRequest(HttpRequest req) throws SQLException {
        DirectiveHandler dirHandler = new DirectiveHandler();
        directiveDAOHandler = dirHandler.getDirectiveObject(req.getDirective());
        chooseDirective(req);
    }

    public void chooseDirective(HttpRequest req) throws SQLException {
        switch(req.getMethod()) {
            case "POST" -> directiveDAOHandler.handlePost(req.getBody());
            case "GET" -> directiveDAOHandler.handleGet(req.getBody());
            case "PUT" -> directiveDAOHandler.handlePut(req.getBody());
            case "DELETE" -> directiveDAOHandler.handleDelete(req.getBody());
        }
    }


}
