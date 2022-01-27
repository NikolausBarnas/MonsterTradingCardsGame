package com.mtcg.repository.directive;

import com.mtcg.repository.game.BattleExchange;

import java.util.Objects;
import java.util.concurrent.SynchronousQueue;

public class DirectiveHandler {

    public DirectiveDAOHandler getDirectiveObject(String directive, String token, SynchronousQueue<BattleExchange> queue) {
        directive = directive.substring(1);
        String addDir = "";
        String[] dirArray;
        dirArray = directive.split("/");

        if (dirArray.length > 1 ) {
            addDir = dirArray[1];
        }

        switch(dirArray[0]) {
            case "users": return new UserHandler(token, addDir);
            case "sessions": return new SessionHandler();
            case "packages":
            case "cards":
                return new CardHandler(token);
            case "transactions": return new TransactionHandler(token);
            case "deck": return new DeckHandler(token,false);
            case "deck?format=plain": return new DeckHandler(token,true);
            case "stats": return new StatsHandler(token);
            case "score": return new ScoreboardHandler(token);
            case "tradings": return new TradeHandler(token, addDir);
            case "battles": return new BattleHandler(token, queue);
            default:
                System.out.println("DIRECTIVE NOT YET IMPLEMENTED");
                break;
        }

        return null;
    }

}
