package com.mtcg.server;

import com.mtcg.repository.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }
    @Override
    public void run() {
        try {
            HttpRequest httpRequest = new HttpRequest(in);
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.chooseDirective(httpRequest.getDirective());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
