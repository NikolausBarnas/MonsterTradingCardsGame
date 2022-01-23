package com.mtcg.repository.directive;


import com.google.gson.Gson;
import com.mtcg.repository.card.Card;
import com.mtcg.repository.card.CardMediator;
import com.mtcg.repository.card.CardRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class CardHandler implements DirectiveDAOHandler {

    private Gson gson = null;
    private CardRepositoryImpl cardRepository = null;
    private ArrayList<Card> cardPackage = new ArrayList<>();

    public CardHandler() {
        this.gson = new Gson();
        this.cardRepository = new CardRepositoryImpl();
    }


    @Override
    public int handlePost(String body) {
        CardMediator[] cardMediators = gson.fromJson(body, CardMediator[].class);
        for (CardMediator c: cardMediators)
            cardPackage.add(c.cardObjectHandler());

        for (Card c: cardPackage) {
            try {
                cardRepository.add(c);
            } catch (SQLException s){
                s.printStackTrace();
            }
        }

        return 200;
    }

    @Override
    public String handleGet(String username) {
        return null;
    }

    @Override
    public int handlePut(String body) {
        return 400;
    }

    @Override
    public int handleDelete(String username) {
        return 400;
    }
}
