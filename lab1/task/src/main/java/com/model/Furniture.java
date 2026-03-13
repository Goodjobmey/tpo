package com.model;

public class Furniture {

    private String name;
    private Condition condition;
    private int durability;

    public Furniture(String name, Condition condition) {
        this.name = name;
        this.condition = condition;
    }

    public Furniture(String name, int durability) {
        this.name = name;
        this.durability = durability;
    }

    public int getDurability() {return durability;}

    public String getName() {
        return name;
    }

    public Condition getCondition() {return condition;}

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}