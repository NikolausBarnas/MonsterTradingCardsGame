package com.mtcg.repository.user;

import com.mtcg.repository.credentials.Credentials;

public class User {
    private Credentials credentials;
    //private Stack stack;

    public User (Credentials credentials) {
        this.credentials = credentials;
    }
}
