package com.mtcg.server.httpRequest;

public class Header {

    private String method;
    private String directive;
    private String token;

    public String getDirective() {
        return directive;
    }

    public String getMethod() {
        return method;
    }

    public String getToken() {
        return token;
    }

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
