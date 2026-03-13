package com.model;

import java.util.List;

public class Person {

    private String name;
    private Emotion emotion;
    private Condition condition;
    private List<BodyPart> bodyParts;
    private Action action;
    private Kingdom kingdom;
    private Integer power;

    // полный конструктор
    public Person(String name, Condition condition, Action action,
                  List<BodyPart> bodyParts, Kingdom kingdom) {
        this.name = name;
        this.condition = condition;
        this.action = action;
        this.bodyParts = bodyParts;
        this.kingdom = kingdom;
    }

    //сокращенный конструктор
    public Person(String name, Condition condition, Action action) {
        this.name = name;
        this.condition = condition;
        this.action = action;
    }

    //сокращенный конструктор
    public Person(String name, Condition condition, Action action, Emotion emotion) {
        this.name = name;
        this.condition = condition;
        this.action = action;
        this.emotion = emotion;
    }

    //сокращенный конструктор
    public Person(String name, Condition condition, Action action, int power) {
        this.name = name;
        this.condition = condition;
        this.action = action;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Emotion getEmotion() {
        return emotion;
    }

    public void setEmotion(Emotion emotion) {
        this.emotion = emotion;
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public Integer getPower() {
        return power;
    }
}