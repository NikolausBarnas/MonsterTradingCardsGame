package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mtcg.repository.card.CardDao;
import com.mtcg.repository.card.CardMediator;
import com.mtcg.server.Response;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeckHandler implements DirectiveDAOHandler{

    private Gson gson = null;
    private CardDao cardDao = null;
    private String token = null;
    private boolean formatPlain;

    public DeckHandler(String token, boolean formatPlain) {
        gson = new Gson();
        this.cardDao = CardDao.getInstance();
        this.token = token;
        this.formatPlain = formatPlain;
    }
    @Override
    public Response handlePost(String body) throws SQLException {
        return new Response(200, "");
    }

    @Override
    public Response handleGet(String username) {
        if (formatPlain) {
            ArrayList<String> deck = new ArrayList<>();
            deck = cardDao.getDeckPlain(token);
            if (deck.isEmpty())
                return new Response(200, "[]");
            return new Response(200, gson.toJson(deck.toArray()));
        }
        ArrayList<CardMediator> deck = CardDao.getInstance().getDeck(token);
        if (deck.isEmpty())
            return new Response(200, "[]");
        return new Response(200, gson.toJson(deck.toArray(), CardMediator[].class));
    }

    @Override
    public Response handlePut(String body) {
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> cardIDs = gson.fromJson(body.toString(),listType);

        if (cardIDs.size() != 4) {
            return new Response(400, "");
        }

        for (String cardID: cardIDs) {
            if (!Objects.equals(CardDao.getInstance().getOwner(cardID), token))
                return new Response(409, "");
        }
        for (String cardID : cardIDs) {
            CardDao.getInstance().updateDeck(cardID,token);
        }
        return new Response(200, "");
    }

    @Override
    public Response handleDelete(String username) {
        return new Response(404, "");
    }
}
