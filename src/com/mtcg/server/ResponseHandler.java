
package com.mtcg.server;

import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseHandler {

    private String responseS;
    private Response response;
    private BufferedWriter b;

    public ResponseHandler(Response r, BufferedWriter b) {
        this.response = r;
        this.b = b;
    }

    public void createResponse() {
        //RESPONSE BASTELN
    }

    public void sendResponse() throws IOException {
        b.write(responseS);
        b.flush();
        b.close();
    }
}
