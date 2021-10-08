package com.mtcg;

public class User {
    Credentials credentials;

    User(String username, String password) {
        credentials = new Credentials(username, password);
    }

}
