package com.mtcg.repository.game;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BattleLog {
    @SerializedName("Player1")
    private String player1;
    @SerializedName("Player2")
    private String player2;
    @SerializedName("Winner")
    private String winner;
    @SerializedName("Rounds")
    private ArrayList<RoundLog> rounds;


    public BattleLog(String player1, String player2, String winner, ArrayList<RoundLog> rounds) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
        this.rounds = rounds;
    }

    public String getWinner() {
        return winner;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}
