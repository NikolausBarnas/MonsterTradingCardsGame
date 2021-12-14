package com.mtcg.server.request;

import java.util.Optional;

public class Header {

    private String method;
    private String directive;
    private Optional<String> token;

    public Header(String method, String directive, Optional<String> token) {
        this.method = method;
        this.directive = directive;
        if (token.isPresent())
            this.token = token;
    }
}
