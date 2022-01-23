package com.mtcg.repository.directive;

import java.sql.SQLException;

public interface DirectiveDAOHandler {
    int handlePost(String body) throws SQLException;
    String handleGet(String body);
    int handlePut(String body);
    int handleDelete(String body);
}
