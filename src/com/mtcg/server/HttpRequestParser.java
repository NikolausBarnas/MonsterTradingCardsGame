package com.mtcg.server;


import com.mtcg.server.httpRequest.Body;
import com.mtcg.server.httpRequest.Header;

import java.io.BufferedReader;
import java.io.IOException;


public class
HttpRequestParser {
    private final BufferedReader in;
    private Header header;
    private Body body;

    public HttpRequestParser(BufferedReader in) {
        this.in = in;
        header = new Header();
        body = new Body();
    }

    public void parseRequest() throws IOException {
        String line;
        int lineCount = 0;
        String[] tempLine;
        while((line = in.readLine()).length() != 0) {
            if (lineCount == 0) {
                tempLine = line.split(" ");
                header.setMethod(tempLine[0]);
                header.setDirective(tempLine[1]);
                lineCount = 1;
            }
            if (line.contains("Authorization")) {
                tempLine = line.split(":");
                header.setToken(tempLine[1].substring(1));
            }
        }

        StringBuilder bodyStringBuilder = new StringBuilder();
        while (in.ready()) {
            bodyStringBuilder.append((char) in.read());
        }
        body.setJsonString(bodyStringBuilder.toString());

    }

}
