package com.mtcg.repository.game;


import java.util.concurrent.CountDownLatch;

public class BattleExchange {

    private String username;
    private CountDownLatch ready;
    private BattleLog battleLog;

    public BattleExchange(String username) {
        this.username = username;
        this.ready = new CountDownLatch(1);
    }

    public void setBattleLog(BattleLog battleLog) {
        this.battleLog = battleLog;
        ready.countDown();
    }

    public BattleLog getBattleLog() {
        try {
            ready.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return battleLog;
    }

    public String getUsername() {
        return username;
    }
}
