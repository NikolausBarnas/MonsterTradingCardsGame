package com.mtcg.repository.trading;

import com.google.gson.annotations.SerializedName;

public class TradingMediator {
    @SerializedName("Id")
    private String id;
    @SerializedName("CardToTrade")
    private String cardToTrade;
    @SerializedName("Type")
    private String type;
    @SerializedName("MinimumDamage")
    private int minimumDamage;

    public TradingMediator(String id, String cardToTrade, String type, int minimumDamage) {
        this.id = id;
        this.cardToTrade = cardToTrade;
        this.type = type;
        this.minimumDamage = minimumDamage;
    }

    public String getId() {
        return id;
    }

    public String getCardToTrade() {
        return cardToTrade;
    }

    public int getMinimumDamage() {
        return minimumDamage;
    }

    public String getType() {
        return type;
    }
}
