package com.mtcg.repository.scoreboard;

public class ScoreboardEntry {

    private int rank;
    private String username;
    private int elo;
    private int gameCount;

    public ScoreboardEntry(int rank, String username, int elo, int gameCount) {
        this.rank = rank;
        this.username = username;
        this.elo = elo;
        this.gameCount = gameCount;
    }
}
