package com.mtcg.repository.game;

import com.google.gson.annotations.SerializedName;

public class RoundLog {
    @SerializedName("Round")
    private int round;
    @SerializedName("Player1")
    private String player1;
    @SerializedName("Player1Card")
    private String player1Card;
    @SerializedName("Player1Damage")
    private double player1Damage;
    @SerializedName("Player2")
    private String player2;
    @SerializedName("Player2Card")
    private String player2Card;
    @SerializedName("Player2Damage")
    private double player2Damage;
    @SerializedName("Round-Conclusion")
    private String roundConclusion;


    public RoundLog(int round, String player1, String player1Card, double player1Damage,
                    String player2, String player2Card, double player2Damage, String roundConclusion) {
        this.round = round;
        this.player1 = player1;
        this.player1Card = player1Card;
        this.player1Damage = player1Damage;
        this.player2 = player2;
        this.player2Card = player2Card;
        this.player2Damage = player2Damage;
        this.roundConclusion = roundConclusion;
    }

}
