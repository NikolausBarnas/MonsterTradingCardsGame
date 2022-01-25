package com.mtcg.repository.directive;


import com.google.gson.Gson;
import com.mtcg.repository.card.Card;
import com.mtcg.repository.card.CardDao;
import com.mtcg.repository.card.CardMediator;
import com.mtcg.repository.packages.Package;
import com.mtcg.repository.packages.PackageDao;
import com.mtcg.server.Response;


import java.util.ArrayList;

public class CardHandler implements DirectiveDAOHandler {

    private Gson gson = null;
    private CardDao cardDao = null;
    private PackageDao packageDao = null;
    private ArrayList<Card> cardPackage = new ArrayList<>();
    private Package p = null;
    private String token;

    public CardHandler(String token) {
        this.gson = new Gson();
        this.cardDao = CardDao.getInstance();
        this.packageDao = PackageDao.getInstance();
        this.token = token;
    }

    @Override
    public Response handlePost(String body) {
        CardMediator[] cardMediators = gson.fromJson(body, CardMediator[].class);
        for (CardMediator c: cardMediators)
            cardPackage.add(c.cardObjectHandler());

        this.p = new Package(cardPackage.get(0).getId(),cardPackage.get(1).getId(),cardPackage.get(2).getId(),
                cardPackage.get(3).getId(),cardPackage.get(4).getId());

        int packageID = packageDao.create(p);
        if (packageID == -1)
            return new Response(500, "");

        for (Card c: cardPackage) {
            c.setPackageID(packageID);
            if (cardDao.create(c) == -1) {
                return new Response(500, "");
            }
        }
        return new Response(200, "");
    }

    @Override
    public Response handleGet(String username) {
        ArrayList<CardMediator> cards = cardDao.getAll(token);
        if (cards.isEmpty())
            return new Response(409, "");

        return new Response(200, gson.toJson(cards.toArray(),CardMediator[].class));
    }

    @Override
    public Response handlePut(String body) {
        return new Response(404, "");
    }

    @Override
    public Response handleDelete(String username) {
        return new Response(404, "");
    }
}
