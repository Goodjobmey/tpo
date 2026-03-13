package com.model;

public class Kingdom {

    private String name;
    private String area;
    private Culture culture;
    private Location location;

    public Kingdom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}