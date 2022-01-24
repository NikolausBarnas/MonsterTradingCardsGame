package com.mtcg.repository.directive;

import com.mtcg.server.Response;

import java.sql.SQLException;

public interface DirectiveDAOHandler {
    Response handlePost(String body) throws SQLException;
    Response handleGet(String body);
    Response handlePut(String body);
    Response handleDelete(String body);
}
