package com.mtcg.repository.directive;

public class DirectiveHandler {

    public DirectiveDAOHandler getDirectiveObject(String directive, String token) {
        switch(directive) {
            case "/users": return new UserHandler();
            case "/sessions": return new SessionHandler();
            case "/packages": return new CardHandler();
            case "/transactions/packages": return new TransactionHandler(token);
            case "/deck": return new DeckHandler(token);
            default:
                System.out.println("DIRECTIVE NOT YET IMPLEMENTED");
                break;
        }

        return null;
    }
}
