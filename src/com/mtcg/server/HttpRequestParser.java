package com.mtcg.server;

import com.mtcg.server.request.Header;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Hashtable;

public class HttpRequestParser {
    private final BufferedReader in;
    public Hashtable<String,String> requestHeader;
    private String body;

    public HttpRequestParser(BufferedReader in) {
        this.in = in;
        requestHeader = new Hashtable<>();
    }

    public void parseRequest() throws IOException {
        String header = in.readLine();
        while(header.length() > 0) {
            appendHeaderParameter(header);
            System.out.println(header);
            header = in.readLine();
        }

        StringBuilder bodyStringBuilder = new StringBuilder();
        while (in.ready()) {
            bodyStringBuilder.append((char) in.read());
        }
        appendBody(bodyStringBuilder);

        System.out.println(requestHeader.toString());
    }


    private void appendHeaderParameter(String header) {
        int index = header.indexOf(":");
        if (index == -1)
            return;

        requestHeader.put(header.substring(0,index), header.substring(index+1));
    }

    private void appendBody(StringBuilder bodyStringBuilder) {
        body = bodyStringBuilder.toString();
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
