package com.task;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TanTest {

    @Test
    @DisplayName("проверка функции")
    void testTgx_1() {
        double x = 0.5;
        int terms = 1;
        assertEquals(0.5, Tan.calculateTan(x, terms), 1e-9);
    }

    @Test
    @DisplayName("проверка функции, когда t = 2")
    void testTgx_2() {
        double x = 0.5;
        int terms = 2;
        double term1 = 0.5;
        double term2 = (1.0/3.0) * Math.pow(0.5, 3);
        assertEquals(term1 + term2, Tan.calculateTan(x, terms));
    }

    @Test
    @DisplayName("проверка функции, когда t = 3")
    void testTgx_3() {
        double x = 0.5;
        int terms = 3;
        double term1 = 0.5;
        double term2 = (1.0/3.0) * Math.pow(0.5, 3);
        double term3 = (2.0/15.0) * Math.pow(0.5, 5);
        assertEquals(term1 + term2 + term3, Tan.calculateTan(x, terms));
    }

    @Test
    @DisplayName("проверка функции, когда x = 0")
    void testTgx_4() {
        double x = 0.0;
        assertEquals(0.0, Tan.calculateTan(x, 1));
        assertEquals(0.0, Tan.calculateTan(x, 2));
        assertEquals(0.0, Tan.calculateTan(x, 3));
    }

    @Test
    @DisplayName("проверка функции, когда x бесконечно большая или маленькая")
    void testTgx_5() {
        double[] infinity = {Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
        for (double x : infinity) {
            assertEquals(Double.NaN, Tan.calculateTan(x, 5));
        }
    }

    @Test
    @DisplayName("проверка функции, когда x = NaN")
    void testTgx_6() {
        double x = Double.NaN;
        assertEquals(Double.NaN, Tan.calculateTan(x, 5));
    }

    @Test
    @DisplayName("проверка функции, когда x приблизительно к пи на 2 слева")
    void testTgx_7() {
        double x = Math.PI/2 - 1e-12;
        double result = Tan.calculateTan(x, 10);
        assertFalse(Double.isInfinite(result));
    }

    @Test
    @DisplayName("проверка функции, когда x приблизительно к пи на 2 справа")
    void testTgx_8() {
        double x = - Math.PI/2 + 1e-12;
        double result = Tan.calculateTan(x, 10);
        assertFalse(Double.isInfinite(result));
    }

    @Test
    @DisplayName("проверка функции, когда x приблизительно к 0 слева")
    void testTgx_9() {
        double x = 1e-12;
        double result = Tan.calculateTan(x, 5);

        assertEquals(x, result, 1e-15);
    }

    @Test
    @DisplayName("проверка функции, когда x приблизительно к 0 справа")
    void testTgx_10() {
        double x = -1e-12;
        double result = Tan.calculateTan(x, 5);

        assertEquals(x, result, 1e-15);
    }

    @Test
    @DisplayName("проверка вывода ошибка функции")
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
    @DisplayName("проверка вывода ошибка функции")
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