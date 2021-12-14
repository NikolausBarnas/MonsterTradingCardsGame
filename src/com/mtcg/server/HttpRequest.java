package com.mtcg.server;

import java.io.BufferedReader;

public class HttpRequest {
    private String method;
    private String directive;
    private String token;
    private String body;

    private HttpRequestParser httpRequestParser;

    public HttpRequest(BufferedReader in) {
        this.httpRequestParser = new HttpRequestParser(in, this);
    }

    public String getDirective() {
        return directive;
    }

    public String getMethod() {
        return method;
    }

    public String getToken() {
        return token;
    }

    public String getBody() {
        return body;
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

    public void setBody(String body) {
        this.body = body;
    }

}
