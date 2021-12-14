package com.mtcg.server.request;

import java.util.Optional;

public class Header {

    private String method;
    private String directive;
    private String token;


    public void setDirective(String directive) {
        this.directive = directive;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
