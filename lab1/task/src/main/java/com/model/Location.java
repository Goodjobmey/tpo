package com.model;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private String name;
    private List<Furniture> furnitures = new ArrayList<>();

    public Location(String name) {
        this.name = name;
    }

    // посмотреть всю мебель
    public List<Furniture> getFurnitures() {
        return furnitures;
    }

    public String getName() {
        return name;
    }
}
