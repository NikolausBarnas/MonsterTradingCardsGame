package com.mtcg.server;


import com.mtcg.server.request.Body;
import com.mtcg.server.request.Header;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.AbstractMap;


public class HttpRequestParser {
    private final BufferedReader in;
    private AbstractMap<String,String> authToken;
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
                System.out.println(tempLine[0]);
                System.out.println(tempLine[1]);
                lineCount = 1;
            }
            if (line.contains("Authorization")) {
                tempLine = line.split(":");
                header.setToken(tempLine[1].substring(1));
                System.out.println(tempLine[1].substring(1));
            }
        }

        StringBuilder bodyStringBuilder = new StringBuilder();
        while (in.ready()) {
            bodyStringBuilder.append((char) in.read());
        }
        appendBody(bodyStringBuilder);

    }

    private void appendBody(StringBuilder bodyStringBuilder) {
        body.setJsonString(bodyStringBuilder.toString());
    }





    /*public void read() throws IOException {
        readHeader();
        readBody();
        System.out.println(body);
    }

    public void readHeader() throws IOException {
        StringBuilder crudStringBuilder = new StringBuilder();
        StringBuilder restStringBuilder = new StringBuilder();
        int subStringIndex;
        int count = 0;
        String result;
        String crud;
        String directive;
        while ((result = in.readLine()).length() != 0) {
            if (count == 0) {
                crudStringBuilder.append(result);
                subStringIndex = crudStringBuilder.toString().indexOf("HTTP");
                result = crudStringBuilder.substring(0,subStringIndex-1);
                subStringIndex = result.indexOf("/");
                directive = result.substring(subStringIndex+1);
                crud = result.substring(0, subStringIndex-1);
                count = 1;
            }
            restStringBuilder.append(result);
        }
        String restString = restStringBuilder.toString();
        if (restString.contains("Authorization")) {
           subStringIndex = restString.indexOf("Authorization");
            result = restString.substring(subStringIndex);
            if (result.contains("Content")) {
                subStringIndex = result.indexOf("Content");
                String contentResult = result.substring(0, subStringIndex);
                header.add(contentResult);
            } else {
                header.add(result);
            }
        }
        Header header = new Header(crud, directive, )
    }

    public void readBody() throws IOException {
        StringBuilder bodyStringBuilder = new StringBuilder();
        while (in.ready()) {
            bodyStringBuilder.append((char) in.read());
        }
        body = bodyStringBuilder.toString();
    }*/

}
