package com.mtcg.repository.directive;

public class DirectiveHandler {

    public DirectiveDAOHandler getDirectiveObject(String directive) {
        switch(directive) {
            case "/users": return new UserHandler();
            case "/sessions": return new SessionHandler();
            case "/packages": return new CardHandler();
            case "transactions/packages":;
            default:
                System.out.println("get directive");
                break;
        }

        return null;
    }
}
