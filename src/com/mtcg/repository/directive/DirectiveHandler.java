package com.mtcg.repository.directive;

public class DirectiveHandler {
    private String directive; 

    public DirectiveHandler(String directive) {
        this.directive = directive;
    }

    public Object getDirectiveObject() {
        switch(directive) {
            case "/users": return new User();
            case "/sessions":;
            case "/packages":;
            case "transactions/packages":;
            default:
                System.out.println("get directive");
                break;
        }

        return null;
    }
}
