package com.mtcg.repository.session;

import com.mtcg.repository.credentials.Credentials;

public interface SessionDao {
    void createSession(Credentials credentials);
    String getSession(Credentials credentials);
}
