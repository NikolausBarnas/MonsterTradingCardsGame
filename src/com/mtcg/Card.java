package com.mtcg;

public abstract class Card {
    Card(String cardName, int damage, ElementType elementType) {
        this.cardName = cardName;
        this.damage = damage;
        this.elementType = elementType;
    }
    final String cardName;
    final int damage;
    final ElementType elementType;
}
