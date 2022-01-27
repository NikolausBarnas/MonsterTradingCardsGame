package com.mtcg.repository.game;

import com.mtcg.repository.card.Card;
import com.mtcg.repository.card.ElementType;

import java.util.ArrayList;

public class Battle implements Runnable {
    private BattleExchange battleExchange1;
    private BattleExchange battleExchange2;

    private Player player1;
    private Player player2;

    private ArrayList<RoundLog> roundLogs = new ArrayList<>();
    private int round;

    public Battle(BattleExchange battleExchange1, BattleExchange battleExchange2) {
        this.battleExchange1 = battleExchange1;
        this.battleExchange2 = battleExchange2;
        this.player1 = new Player(battleExchange1.getUsername());
        this.player2 = new Player(battleExchange2.getUsername());
        this.round = 0;
    }

    @Override
    public void run() {
        String winnerName = "draw";

        while (round < 100) {
            round++;
            if (player1.getDeckSize() == 0) {
                winnerName = player2.getUsername();
                break;
            }
            if (player2.getDeckSize() == 0) {
                winnerName = player1.getUsername();
                break;
            }
            playRound();
        }


        BattleLog battleLog = new BattleLog(player1.getUsername(), player2.getUsername(),winnerName,roundLogs);
        battleExchange1.setBattleLog(battleLog);
        battleExchange2.setBattleLog(battleLog);
    }

    private void playRound() {

        String roundConclusion = "draw";
        boolean isSpellOrMixedFight = false;
        Card player1Card = player1.getRandomCard();
        Card player2Card = player2.getRandomCard();

        double player1RealDamage = player1Card.getDamage();
        double player2RealDamage =  player2Card.getDamage();

        if (player1Card.getName().contains("Spell") || player2Card.getName().contains("Spell")) {
            isSpellOrMixedFight = true;

            ElementType card1Element = player1Card.getElementType();
            ElementType card2Element = player2Card.getElementType();

            int cardEffectiveness = getEffectiveness(card1Element, card2Element);
            RealDamage realDamage = calculateRealDamage(cardEffectiveness, player1RealDamage, player2RealDamage);
            player1RealDamage = realDamage.getPlayer1RealDamage();
            player2RealDamage = realDamage.getPlayer2RealDamage();
        }

        int check = checkSpeciality(player1Card.getName(), player2Card.getName());
        switch (check) {
            case 1: player2RealDamage = 0; break;
            case 2: player1RealDamage = 0; break;
        }

        if (player1RealDamage > player2RealDamage) {
            roundConclusion = "Player1 wins Round";
            switchCardOwner(player2Card, 2);
        } else if (player1RealDamage < player2RealDamage) {
            roundConclusion = "Player2 wins Round";
            switchCardOwner(player1Card, 1);
        }
        roundLogs.add(new RoundLog(round,player1.getUsername(),player1Card.getName(),player1RealDamage,
                player2.getUsername(),player2Card.getName(),player2RealDamage,roundConclusion));

    }

    private int checkSpeciality(String card1, String card2) {
        for (int i = 0; i < 2; i++) {
            if (card1.contains("Dragon")) {
                if (card2.contains("Goblin")) {
                    return 1 + i;
                } else if (card2.contains("FireElf")) {
                    if (i == 0)
                        return 2;
                    return 1;
                }
            } else if (card1.contains("Wizard") && card2.contains("Ork")) {
                return 1 + i;
            } else if (card1.contains("Knight") && card2.contains("WaterSpell")) {
                if (i == 0)
                    return 2;
                return 1;
            } else if (card1.contains("Kraken") && card2.contains("Spell")) {
                return 1 + i;
            }
        }
        return 0;
    }

    private RealDamage calculateRealDamage(int card1Effectiveness, double player1RealDamage, double player2RealDamage) {
        if (card1Effectiveness == 1) {
            player1RealDamage = player1RealDamage*2;
            player2RealDamage = player2RealDamage/2;
        } else if (card1Effectiveness == 2) {
            player2RealDamage = player2RealDamage*2;
            player1RealDamage = player1RealDamage/2;
        }
        return new RealDamage(player1RealDamage, player2RealDamage);
    }

    private void switchCardOwner(Card card, int currentOwner) {
        int newOwner = (currentOwner % 2) + 1;

        if (newOwner == 1) {
            player1.addCard(card);
            player2.removeCard(card);
        } else {
            player2.addCard(card);
            player1.removeCard(card);
        }
    }

    private int getEffectiveness(ElementType element1, ElementType element2) {
        switch(element1) {
            case FIRE -> {
                if (element2 == ElementType.WATER) return 2;
                else if (element2 == ElementType.NORMAL) return 1;
            }
            case WATER -> {
                if (element2 == ElementType.FIRE) return 1;
                else if (element2 == ElementType.NORMAL) return 2;
            }
            case NORMAL -> {
                if (element2 == ElementType.FIRE) return 2;
                else if (element2 == ElementType.WATER) return 1;
            }
        }
        return 0;
    }
}
