package com.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TanTest {

    @Test
    void testTgx_1() {
        double x = 0.5;
        int terms = 1;
        assertEquals(0.5, Tan.calculateTan(x, terms), 1e-9);
    }

    @Test
    void testTgx_2() {
        double x = 0.5;
        int terms = 2;
        double term1 = 0.5;
        double term2 = (1.0/3.0) * Math.pow(0.5, 3);
        assertEquals(term1 + term2, Tan.calculateTan(x, terms));
    }

    @Test
    void testTgx_3() {
        double x = 0.5;
        int terms = 3;
        double term1 = 0.5;
        double term2 = (1.0/3.0) * Math.pow(0.5, 3);
        double term3 = (2.0/15.0) * Math.pow(0.5, 5);
        assertEquals(term1 + term2 + term3, Tan.calculateTan(x, terms));
    }

    @Test
    void testTgx_4() {
        double x = 0.0;
        assertEquals(0.0, Tan.calculateTan(x, 1));
        assertEquals(0.0, Tan.calculateTan(x, 2));
        assertEquals(0.0, Tan.calculateTan(x, 3));
    }

    @Test
    void testTgx_error1() {
        double x = 0.5;
        int invalidTerms = 0;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Tan.calculateTan(x, invalidTerms)
        );
        assertEquals("Число развернутых членов должно быть больше или равно 1", exception.getMessage());
    }

    @Test
    void testTgx_error2() {
        double x = 4;
        int invalidTerms = 2;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Tan.calculateTan(x, invalidTerms)
        );
        assertEquals("|x| должно меньше чем π/2", exception.getMessage());
    }

}