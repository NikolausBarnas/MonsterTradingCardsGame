package com.mtcg.repository.card;

import com.google.gson.annotations.SerializedName;

public class CardMediator {
    @SerializedName("Id")
    private String id;
    @SerializedName("Name")
    private String name;
    @SerializedName("Damage")
    private double damage;

    public CardMediator(String id, String name, double damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
    }

    public ElementType getElementType() {
        //getelementtype
        switch(name.charAt(0)) {
            case 'W': return ElementType.WATER;
            case 'F': return ElementType.FIRE;
            default:  return ElementType.NORMAL;
        }
    }

    public Card cardObjectHandler() {
        Card card;
        if (this.name.contains("Spell")) {
            card = new SpellCard(this.id, this.name, this.damage, this.getElementType(), CardType.SPELL);
        }
        else {
            card = new MonsterCard(this.id, this.name, this.damage, this.getElementType(), CardType.MONSTER);
        }
        return card;
    }
}
