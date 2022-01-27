package com.mtcg.server;

import com.mtcg.repository.RequestHandler;
import com.mtcg.repository.game.BattleExchange;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.SynchronousQueue;


public class ClientHandler implements Runnable {
    private Socket client;
    private BufferedReader in;
    private BufferedWriter out;
    private SynchronousQueue<BattleExchange> queue;

    public ClientHandler(Socket clientSocket, SynchronousQueue<BattleExchange> queue) throws IOException {
        this.client = clientSocket;
        this.queue = queue;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
    }
    @Override
    public void run() {
        try {
            HttpRequest httpRequest = new HttpRequest(in);
            RequestHandler requestHandler = new RequestHandler(queue);
            ResponseHandler r = new ResponseHandler(requestHandler.handleRequest(httpRequest),out);
            r.createResponse();
            r.sendResponse();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
