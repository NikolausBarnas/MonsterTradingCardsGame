package com.mtcg.repository.scoreboard;

public class ScoreboardEntry {

    private int rank;
    private String username;
    private int elo;

    public ScoreboardEntry(int rank, String username, int elo) {
        this.rank = rank;
        this.username = username;
        this.elo = elo;
    }
}
