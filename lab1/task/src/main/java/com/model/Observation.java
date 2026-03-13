package com.model;

import com.model.interfaces.Changeble;

public class Observation implements Changeble {

    private Person observer;
    private Object observedObject;
    private Reaction reaction;

    public Observation(Person observer, Object observedObject) {
        this.observer = observer;
        this.observedObject = observedObject;
    }

    public void setReaction(Reaction reaction) {
        this.reaction = reaction;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public Person getObserver() {
        return observer;
    }
}