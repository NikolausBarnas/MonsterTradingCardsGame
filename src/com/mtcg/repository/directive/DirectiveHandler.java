package com.mtcg.repository.directive;

public class DirectiveHandler {
    private String directive;

    public DirectiveHandler(String directive) {
        this.directive = directive;
    }

    public Object getDirectiveObject() {
        switch(directive) {
            case "/users":;
            case "/sessions":;
            case "/packages":;
            default:
                System.out.println("get directive");
                break;
        }

        return null;
    }
}
