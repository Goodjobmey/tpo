package com.model;

public class BodyPart {

    private String name;
    private String side;

    public BodyPart(String name, String side) {
        this.name = name;
        this.side = side;
    }

    public BodyPart(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getSide() {
        return side;
    }
}