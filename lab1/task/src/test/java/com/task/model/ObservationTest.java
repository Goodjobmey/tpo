package com.task.model;

import com.model.Condition;
import com.model.Observation;
import com.model.Person;
import com.model.Reaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ObservationTest {

    @Test
    void observationReactionTest(){
        System.out.println("TEST 1 . . .");

        Person arthur = new Person("Arthur",null,null,null);
        Person creature = new Person("Сreature",null,null,null,null);
        Reaction reaction = new Reaction("shock");
        Condition condition = new Condition("shock");


        Observation observation = new Observation(arthur,creature);

        observation.setReaction(reaction);
        reaction.changeCondition(arthur, condition);

        assertEquals("shock", observation.getReaction().getName());
        assertEquals("Arthur", observation.getObserver().getName());
        assertEquals("shock", arthur.getCondition().getName());
        System.out.println("passed");
    }
}