package com.mtcg.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Server {
    private final int PORT = 10001;

    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(100);

    public Server() {

        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                Socket client = listener.accept();
                ClientHandler clientThread = new ClientHandler(client);
                clients.add(clientThread);
                pool.execute(clientThread);
            }
        } catch (IOException e) {

        }
    }

}
