package com.mtcg.repository.packages;

import java.util.ArrayList;

public class Package {
    private ArrayList<String> cards;

    public Package(String card1, String card2, String card3, String card4, String card5) {
        cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);
    }

    public ArrayList<String> getCards() {
        return cards;
    }
}
