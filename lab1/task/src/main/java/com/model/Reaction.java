package com.model;

import com.model.interfaces.Changeble;

public class Reaction implements Changeble {

    private String name;

    public Reaction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}