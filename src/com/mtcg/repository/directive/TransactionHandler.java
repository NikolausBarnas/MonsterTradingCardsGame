package com.mtcg.repository.directive;

import com.google.gson.Gson;
import com.mtcg.repository.card.CardDao;
import com.mtcg.repository.packages.Package;
import com.mtcg.repository.packages.PackageDao;
import com.mtcg.repository.transaction.TransactionDao;
import com.mtcg.repository.user.UserDao;
import com.mtcg.server.Response;


import java.sql.SQLException;

import java.util.Optional;

public class TransactionHandler implements DirectiveDAOHandler {
    private Gson gson = null;
    private CardDao cardDao = null;
    private PackageDao packageDao = null;
    private Package p = null;
    TransactionDao transactionDao = null;
    private String token = null;
    private UserDao userDao = null;

    public TransactionHandler(String token) {
        this.gson = new Gson();
        this.cardDao = CardDao.getInstance();
        this.packageDao = PackageDao.getInstance();
        this.p = new Package("", "", "", "", "");
        this.transactionDao = TransactionDao.getInstance();
        this.token = token;
        this.userDao = UserDao.getInstance();
    }


    @Override
    public Response handlePost(String body) throws SQLException {
        int coins = 0;
        if ((coins = userDao.getCoins(token)) < 5)
            return new Response(409, "");

        Optional<Package> buyingPackage = packageDao.read();
        if (!buyingPackage.isPresent())
            return new Response(500,"");
        packageDao.delete(p);
        for (int i = 0; i < buyingPackage.get().getCards().size(); i++) {
            transactionDao.update(buyingPackage.get().getCards().get(i), token);
        }
        if (userDao.updateCoins(token, coins-5) == -1) {
            return new Response(500,"");
        }

        return new Response(200, "");
    }

    @Override
    public Response handleGet(String username) {
        return new Response(404, "");
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
