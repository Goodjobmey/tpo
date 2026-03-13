package com.model.interfaces;

import com.model.*;

public interface Changeble extends Usable {

    // изменение состояния
    default void changeCondition(Person p, Condition c){
        p.setCondition(c);
    }
    // изменение состояния
    default void changeCondition(Furniture f, Condition c){
        f.setCondition(c);
    }

    // открывание двери
    default void openDoor(Person p, Furniture f) {
        int power = p.getPower()/f.getDurability();

        Condition c1 = new Condition("closed");
        Condition c2 = new Condition("opened");
        Condition c3 = new Condition("broken");

        if (power >= 0 && power <= 2) {
            f.setCondition(c1);
        }
        else if (power > 2 && power <= 5) {
            f.setCondition(c2);
        }
        else if (power > 5) {
            f.setCondition(c3);
        }
    }

    // изменение эмоции
    default void changeEmotion(Person p, Emotion e){
        p.setEmotion(e);
    }
}
