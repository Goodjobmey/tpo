package com.model;

public class Furniture {

    private String name;
    private Condition condition;

    public Furniture(String name, Condition condition) {
        this.name = name;
        this.condition = condition;
    }

    public String getName() {
        return name;
    }

    public Condition getCondition() {return condition;}

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}