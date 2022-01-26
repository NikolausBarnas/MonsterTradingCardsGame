package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.TradingMediator.TradeDao;
import com.mtcg.repository.TradingMediator.TradingMediator;
import com.mtcg.repository.card.CardDao;
import com.mtcg.repository.card.CardMediator;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsDaoImpl;
import com.mtcg.repository.transaction.TransactionDao;
import com.mtcg.repository.user.UserDao;
import com.mtcg.repository.user.UserMediator;
import com.mtcg.server.Response;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class TradeHandler implements DirectiveDAOHandler {
    private Gson gson = null;
    private String token;
    private String addDir;

    public TradeHandler(String token, String addDir) {
        this.gson = new Gson();
        this.token = token;
        this.addDir = addDir;
    }

    @Override
    public Response handlePost(String body) {
        TradingMediator tradingMediator;
        if (addDir.isEmpty()) {
            tradingMediator = gson.fromJson(body, TradingMediator.class);
            if (CardDao.getInstance().isInDeck(tradingMediator.getCardToTrade()))
                return new Response(409, "");
            if (!CardDao.getInstance().getOwner(tradingMediator.getCardToTrade()).equals(token))
                return new Response(409, "");
            if(TradeDao.getInstance().createTradeOffer(token, gson.fromJson(body, TradingMediator.class)) == -1) {
                return new Response(500, "");
            }
            return new Response(200, "");
        }


        body = body.replaceAll("\"","");

        if (CardDao.getInstance().isInDeck(body))
            return new Response(409, "");

        if (!Objects.equals(CardDao.getInstance().getOwner(body), token))
            return new Response(409, "");

        CardMediator cardMediator = CardDao.getInstance().selectCard(body);
        tradingMediator = TradeDao.getInstance().getTrading(addDir);

        if (tradingMediator == null)
            return new Response(404, "");

        String ownerCardToTrade = CardDao.getInstance().getOwner(tradingMediator.getCardToTrade());

        if (Objects.equals(ownerCardToTrade, token))
            return new Response(409, "");

        if (!meetsRequirements(cardMediator, tradingMediator))
            return new Response(401, "");

        TransactionDao.getInstance().update(tradingMediator.getCardToTrade(), token);
        TransactionDao.getInstance().update(body,ownerCardToTrade);

        TradeDao.getInstance().deleteTrading(tradingMediator.getId());

        return new Response(200, "");
    }

    @Override
    public Response handleGet(String username) {
        ArrayList<TradingMediator> tradingMediators = TradeDao.getInstance().getTradings(token);
        if (tradingMediators == null)
            return new Response(500, "");
        if (tradingMediators.isEmpty()) {
            return new Response(200, "[]");
        }
        return new Response(200, gson.toJson(tradingMediators.toArray(),TradingMediator[].class));
    }

    @Override
    public Response handlePut(String body) {
        return new Response(404, "");
    }

    @Override
    public Response handleDelete(String username) {
        if (TradeDao.getInstance().getOwner(addDir).equals(token)) {
            if ( TradeDao.getInstance().deleteTrading(addDir) == -1)
                return new Response(500, "");
            return new Response(200, "");
        }
        return new Response(401, "");
    }

    private boolean meetsRequirements(CardMediator cardMediator, TradingMediator tradingMediator) {
        if (tradingMediator.getMinimumDamage() > cardMediator.getDamage())
            return false;
        if (tradingMediator.getType().equals("monster")) {
            if (cardMediator.getName().contains("Spell"))
                return false;
        }
        if (tradingMediator.getType().equals("spell")) {
            return cardMediator.getName().contains("Spell");
        }
        return true;
    }

}
