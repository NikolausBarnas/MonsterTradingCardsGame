package com.mtcg.repository.game;

import com.mtcg.repository.card.Card;
import com.mtcg.repository.card.CardDao;
import com.mtcg.repository.card.CardMediator;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    private String username;
    private ArrayList<Card> deck;
    private Random random;

    public Player(String username) {
        this.username = username;
        this.deck = new ArrayList<Card>();
        getDeck();
        random = new Random();
    }

    public void getDeck() {
        ArrayList<CardMediator> deckMediator = CardDao.getInstance().getDeck(username);
        for (CardMediator c: deckMediator) {
            deck.add(c.cardObjectHandler());
        }
    }

    public Card getRandomCard() {
        if (deck.size() == 0)
            return null;
        int cardIndex = random.nextInt(deck.size());
        return deck.get(cardIndex);
    }

    public void removeCard(Card card) {
        deck.remove(card);
    }

    public void addCard(Card card) {
        deck.add(card);
    }

    public int getDeckSize() {
        return deck.size();
    }

    public String getUsername() {
        return this.username;
    }
}
