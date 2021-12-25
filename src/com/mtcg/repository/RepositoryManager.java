package com.mtcg.repository;

import com.google.gson.Gson;
import com.mtcg.hashing.Hash;
import com.mtcg.repository.card.Card;
import com.mtcg.repository.card.CardMediator;
import com.mtcg.repository.card.CardRepositoryImpl;
import com.mtcg.repository.session.SessionDaoImpl;
import com.mtcg.repository.credentials.Credentials;
import com.mtcg.repository.credentials.CredentialsRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class RepositoryManager {

    public void chooseDirective(String method) {
        switch(method) {
            case "POST":
                break;
        }
    }




  /*  private ArrayList<String> header;
    private String body;
    private CredentialsRepositoryImpl credentialsRepository;
    private CardRepositoryImpl cardRepository;
    private ArrayList<Card> cardArrayList = new ArrayList<>();
    private Gson gson = new Gson();
    Credentials credentials;
    Hash hash;

    public RepositoryManager(ArrayList<String> header, String body) throws SQLException {
        this.header = header;
        this.body = body;
        this.credentialsRepository = CredentialsRepositoryImpl.getInstance();
        this.cardRepository = CardRepositoryImpl.getInstance();
        this.handleBody();
        this.hash = Hash.getInstance();
        manageDirective();
    }

    private void handleBody() {
        if (!body.contains("[")) {
            this.credentials = gson.fromJson(body, Credentials.class);
        } else if (body.contains("[") && body.contains("{")) {
            CardMediator[] cardMediatorArray = gson.fromJson(body,CardMediator[].class);
            for (CardMediator c: cardMediatorArray) {
                cardArrayList.add(c.cardObjectHandler());
            }
        } else if (body.contains("[") && !body.contains("{")) {

        }
    }

    private void manageDirective() throws SQLException {
        //Directive Classes
        switch (this.header.get(1)) {
            case "users": manageUserDirective(); break;
            case "sessions": manageSessionDirective(); break;
            case "packages": managePackageDirective(); break;
            default:
                System.out.println("WORK IN PROGRESS"); break;
        }
    }

    private void manageUserDirective() throws SQLException {
        switch(this.header.get(0)) {
            case "POST":
                credentials.setHashPassword(hash.generatePasswordHash(credentials.getPassword()));
                credentialsRepository.add(credentials);
            break;
            default:
                System.out.println("WORK IN PROGRESS"); break;
        }
    }

    private void manageSessionDirective() throws SQLException {
        switch(this.header.get(0)) {
            case "POST":
                if (credentialsRepository.get(credentials).isPresent()) {
                    if ((hash.checkPassword(credentialsRepository.get(credentials).get().getPassword(), credentials.getPassword()))) {
                        if (Objects.equals(SessionDaoImpl.getInstance().getSession(credentials), ""))
                            SessionDaoImpl.getInstance().createSession(credentials);
                        else
                            System.out.println("USER IS LOGGED IN ALREADY");
                    } else {
                        System.out.println("401 UNAUTHORIZED");
                    }
                }
            break;
            default: break;
        }
    }

    private void managePackageDirective() throws SQLException {
        switch(this.header.get(0)) {
            case "POST":
                for (Card c: cardArrayList)
                    cardRepository.add(c);
            break;
            default: break;
        }
    }*/

}
