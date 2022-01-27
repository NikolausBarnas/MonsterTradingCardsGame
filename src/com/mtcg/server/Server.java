package com.mtcg.server;
import com.mtcg.repository.game.BattleExchange;
import com.mtcg.repository.game.GameInitializer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;


public class Server {
    private final int PORT = 10001;
    private static int POOL = 100;

    private ArrayList<ClientHandler> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(POOL);
    private SynchronousQueue<BattleExchange> queue = new SynchronousQueue<>();
    private Thread gameInitializer;

    public Server() {

        gameInitializer = new Thread(new GameInitializer(queue));
        gameInitializer.start();

        try (ServerSocket listener = new ServerSocket(PORT)) {
            while (true) {
                Socket client = listener.accept();
                ClientHandler clientThread = new ClientHandler(client, queue);
                clients.add(clientThread);
                pool.execute(clientThread);
            }
        } catch (IOException e) {

        }
    }

}
