package com.model;

public interface Changeble extends Usable {

    // изменение состояния
    default void changeCondition(Person p, Condition c){
        p.setCondition(c);
    }
    // изменение состояния
    default void changeCondition(Furniture f, Condition c){
        f.setCondition(c);
    }

    // изменение эмоции
    default void changeEmotion(Person p, Emotion e){
        p.setEmotion(e);
    }
}
