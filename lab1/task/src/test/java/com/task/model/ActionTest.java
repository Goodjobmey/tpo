package com.task.model;

import com.model.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ActionTest {

    //чек связей
    @Test
    void bodyPartActionTest1(){

        System.out.println("TEST 1 . . .");

        Condition с1 = new Condition("integrity");
        Condition с2 = new Condition("broken");

        BodyPart hand = new BodyPart("hand","left");
        Furniture toothbrush = new Furniture("toothbrush", с1);

        Action action = new Action("pick teeth", hand);

        //action.useFurniture(toothbrush); //тут хз че сделать
        action.changeCondition(toothbrush, с2);

        assertEquals("broken", toothbrush.getCondition().getName());

        System.out.println("passed");
    }

    // чек изменения состояния
    @Test
    void bodyPartActionTest2(){

        System.out.println("TEST 2 . . .");

        Condition c1 = new Condition("calm");
        Condition с2 = new Condition("shock");

        BodyPart chin = new BodyPart("chin");

        Action action = new Action("jaw drooping", chin);

        Person person = new Person("Arthur", c1, action);

        action.changeCondition(person, с2);

        assertEquals("shock", person.getCondition().getName());
        System.out.println("passed");
    }

    // чек изменения эмоции
    @Test
    void bodyPartActionTest3(){

        System.out.println("TEST 3 . . .");

        Emotion e1 = new Emotion("happiness");
        Emotion e2 = new Emotion("fear");
        Condition calm = new Condition("calm");


        Action action = new Action("joke");

        Person person = new Person("Arthur", calm, action, e1);

        action.changeEmotion(person, e2);

        assertEquals("fear", person.getEmotion().getName());
        System.out.println("passed");
    }
}