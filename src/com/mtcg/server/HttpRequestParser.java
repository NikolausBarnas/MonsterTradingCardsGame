package com.mtcg.server;


import java.io.BufferedReader;
import java.io.IOException;


public class HttpRequestParser {
    private final BufferedReader in;
    private final HttpRequest httpRequest;

    public HttpRequestParser(BufferedReader in, HttpRequest httpRequest) {
        this.in = in;
        this.httpRequest = httpRequest;
    }

    public void parseRequest() throws IOException {
        String line;
        int lineCount = 0;
        String[] tempLine;
        while((line = in.readLine()).length() != 0) {
            if (lineCount == 0) {
                tempLine = line.split(" ");
                httpRequest.setMethod(tempLine[0]);
                httpRequest.setDirective(tempLine[1]);
                lineCount = 1;
            }
            if (line.contains("Authorization")) {
                tempLine = line.split(":");
                httpRequest.setToken(tempLine[1].substring(1));
            }
        }

        StringBuilder bodyStringBuilder = new StringBuilder();
        while (in.ready()) {
            bodyStringBuilder.append((char) in.read());
        }
        httpRequest.setBody(bodyStringBuilder.toString());

    }

    public HttpRequest getHttpRequest() {
        return httpRequest;
    }
}