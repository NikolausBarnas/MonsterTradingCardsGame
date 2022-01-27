package com.mtcg.repository.game;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

public class GameInitializer implements Runnable {

    SynchronousQueue<BattleExchange> queue;
    private ArrayList<BattleExchange> waiting;

    public GameInitializer(SynchronousQueue<BattleExchange> queue) {
        this.queue = queue;
        this.waiting = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            try {
                BattleExchange battleExchange = queue.take();
                waiting.add(battleExchange);
                if (waiting.size() == 2) {
                    Thread battle = new Thread(new Battle(waiting.get(0), waiting.get(1)));
                    waiting.clear();
                    battle.start();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

