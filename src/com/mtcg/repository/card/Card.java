package com.mtcg.repository.card;

import com.google.gson.annotations.SerializedName;

public abstract class Card {
    @SerializedName("Id")
    protected final String id;
    @SerializedName("Name")
    protected final String name;
    @SerializedName("Damage")
    protected final double damage;
    protected ElementType elementType;
    protected CardType cardType;
    protected int packageID;

    Card(String id, String name, double damage, ElementType elementType, CardType cardType) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.elementType = elementType;
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getDamage() {
        return damage;
    }

    public CardType getCardType() {
        return cardType;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }
}
