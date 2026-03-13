package com.task.model;

import com.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {

    //просто чекаем как создается объект
    @Test
    void personCreationTest() {

        System.out.println("TEST 1 . . .");
        Condition condition = new Condition("calm");
        BodyPart head1 = new BodyPart("head","left");
        BodyPart head2 = new BodyPart("head","right");

        Kingdom kingdom = new Kingdom("Lilipytia");

        Person person = new Person(
                "Creature",
                condition,
                null,
                List.of(head1, head2),
                kingdom
        );

        assertEquals("Creature", person.getName());
        assertEquals("Lilipytia", person.getKingdom().getName());
        assertEquals("calm", person.getCondition().getName());
        System.out.println("passed");
    }
}