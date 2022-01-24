package com.mtcg.server;

public class Response {

    private int statusCode;
    private String jsonString;

    public Response (int statusCode, String jsonString) {
        this.statusCode = statusCode;
        this.jsonString = jsonString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getJsonString() {
        return jsonString;
    }
}
