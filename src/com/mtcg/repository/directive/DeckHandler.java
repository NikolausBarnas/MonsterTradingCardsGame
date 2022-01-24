package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mtcg.repository.card.CardDao;
import com.mtcg.server.Response;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeckHandler implements DirectiveDAOHandler{

    private Gson gson = null;
    private CardDao cardDao = null;
    private String token = null;

    public DeckHandler(String token) {
        gson = new Gson();
        this.cardDao = CardDao.getInstance();
        this.token = token;

    }
    @Override
    public Response handlePost(String body) throws SQLException {
        return new Response(200, "");
    }

    @Override
    public Response handleGet(String username) {
        ArrayList<String> deck = new ArrayList<>();
        deck = cardDao.getDeck(username);
        if (deck.isEmpty())
            return new Response(404, "");

        return new Response(200, gson.toJson(deck.toArray()));
    }

    @Override
    public Response handlePut(String body) {
        Type listType = new TypeToken<List<String>>(){}.getType();
        List<String> cardIDs = gson.fromJson(body.toString(),listType);
        if (cardIDs.size() != 4) {
            //KORREKTER STATUSCODE FEHLT -> DECK HAT ZU WENIGE/ZU VIELE KARTEN
            return new Response(400, "");
        }
        for (String cardID : cardIDs) {
            CardDao.getInstance().updateDeck(cardID,token);
        }
        return new Response(404, "");
    }

    @Override
    public Response handleDelete(String username) {
        return new Response(404, "");
    }
}
