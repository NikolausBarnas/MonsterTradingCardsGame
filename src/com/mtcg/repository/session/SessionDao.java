package com.mtcg.repository.session;

import com.mtcg.repository.credentials.Credentials;

public interface SessionDao {
    boolean createSession(Credentials credentials);
    String getSession(String username);
}
