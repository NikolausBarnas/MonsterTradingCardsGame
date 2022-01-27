package com.mtcg.repository.game;

public class RealDamage {
    private double player1RealDamage;
    private double player2RealDamage;

    public RealDamage(double player1RealDamage, double player2RealDamage) {
        this.player1RealDamage = player1RealDamage;
        this.player2RealDamage = player2RealDamage;
    }

    public double getPlayer1RealDamage() {
        return player1RealDamage;
    }

    public double getPlayer2RealDamage() {
        return player2RealDamage;
    }
}
