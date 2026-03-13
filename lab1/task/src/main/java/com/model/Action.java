package com.model;

public class Action implements Changeble{

    private String name;
    private BodyPart bodyPart;

    //конструктор
    public Action(String name, BodyPart bodyPart) {
        this.name = name;
        this.bodyPart = bodyPart;
    }

    public Action(String name) {this.name = name;}

    public String getName() {
        return name;
    }
}