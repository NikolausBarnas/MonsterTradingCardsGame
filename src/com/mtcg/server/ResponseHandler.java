
package com.mtcg.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ResponseHandler {

    private String responseString;
    private Response response;
    private BufferedWriter b;

    public ResponseHandler(Response r, BufferedWriter b) {
        this.response = r;
        this.b = b;
    }

    public void createResponse() {
        StringBuilder s = new StringBuilder();
        s.append("HTTP/1.1 ").append(response.getStatusCode() + " ").append(getStatus() + "\r\n");
        if (!response.getJsonString().isEmpty()) {
            s.append("Content-Length: ").append(response.getJsonString().length()).append("\r\n");
            s.append("Content-Type: application/json\r\n");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        s.append("Date: ").append(formatter.format(new Date())).append(" GMT\r\n")
                .append("Server: MTCG-Server\r\n");
        if (!response.getJsonString().isEmpty()) {
            s.append("\r\n").append(response.getJsonString());
        }
        responseString = s.toString();
    }

    public void sendResponse() throws IOException {
        b.write(responseString);
        b.flush();
        b.close();
    }

    public String getStatus() {
        return switch(response.getStatusCode())
            {
                case 200 -> "OK";
                case 201 -> "Created";
                case 202 -> "Accepted";
                case 203 -> "Non-Authoritative Information";
                case 204 -> "No Content";
                case 400 -> "Bad Request";
                case 401 -> "Unauthorized";
                case 403 -> "Forbidden";
                case 404 -> "Not Found";
                case 409 -> "Conflict";
                case 500 -> "Internal Server Error";
                default -> "Unknown";
            };
    }

}
